package com.example.finpos_flutter;

import android.app.Activity;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.finpos_flutter.print.PrintHelper;

import global.citytech.finpossmart.sdk.FinPosSmartSdk;
import global.citytech.finpossmart.sdk.api.SmartSdk;
import global.citytech.finpossmart.sdk.api.printer.PrintRequest;
import global.citytech.finpossmart.sdk.api.printer.PrinterResponse;
import global.citytech.payment.sdk.api.PaymentService;
import global.citytech.payment.sdk.api.PosPaymentApi;
import global.citytech.payment.sdk.api.PosPaymentConfiguration;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

/**
 * FinposFlutterPlugin
 */
public class FinposFlutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware {
    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private MethodChannel channel;
    private Activity activity;

    SmartSdk smartSdk;
    PaymentService paymentService;

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "finpos_flutter");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        switch (call.method) {
            case "getPlatformVersion":
                result.success("Android " + android.os.Build.VERSION.RELEASE);
                break;
            case "initializeSdks":
                paymentService = PosPaymentApi.getInstance(activity, new PosPaymentConfiguration("dynamic", "1", "test", "test"));
                smartSdk = FinPosSmartSdk.getInstance(activity);
                result.success("Sdks initialization success");
                break;
            case "feedPaper":
                Thread feedPaperThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        smartSdk.feedPaper(10);
                    }
                };
                feedPaperThread.start();
                break;
            case "checkPrinterStatus":
                Thread printerStatusThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        PrinterResponse printResponse = smartSdk.getPrinterStatus();
                        Log.d("FINPOS", "Print Result::: " + printResponse.getResult().getMessage());
                        Log.d("FINPOS", "Print Message:::" + printResponse.getMessage());
                    }
                };
                printerStatusThread.start();
                break;
            case "startPrinting":
                Thread printThread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            PrintRequest printRequest = PrintHelper.preparePrintRequest(activity);
                            PrinterResponse printResponse = smartSdk.print(printRequest);
                            Log.d("FINPOS", "Print Result::: " + printResponse.getResult().getMessage());
                            Log.d("FINPOS", "Print Message:::" + printResponse.getMessage());

                        } catch (Exception exception) {
                            exception.printStackTrace();
                            Log.d("FINPOS", "Print Message:::" + exception.getMessage());
                        }
                    }
                };
                printThread.start();
                break;
            default:
                result.notImplemented();
                break;
        }
    }

    @Override
    public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }

    @Override
    public void onAttachedToActivity(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();

    }

    @Override
    public void onDetachedFromActivityForConfigChanges() {
        activity = null;

    }

    @Override
    public void onReattachedToActivityForConfigChanges(@NonNull ActivityPluginBinding binding) {
        activity = binding.getActivity();

    }

    @Override
    public void onDetachedFromActivity() {
        activity = null;
    }
}
