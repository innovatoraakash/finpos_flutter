package com.example.finpos_flutter;

import android.app.Activity;

import androidx.annotation.NonNull;

import global.citytech.finpossmart.sdk.FinPosSmartSdk;
import global.citytech.finpossmart.sdk.api.SmartSdk;
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

    @Override
    public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
        channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "finpos_flutter");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        SmartSdk smartSdk;
        PaymentService paymentService;
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else if (call.method.equals("posPaymentSdkInitialize")) {
            paymentService = PosPaymentApi.getInstance(activity, new PosPaymentConfiguration("dynamic", "1", "test", "test"));
            smartSdk = FinPosSmartSdk.getInstance(activity);
            result.success("Android iniialization success");
        } else if (call.method.equals("finPosSdkInitialize")) {
            smartSdk = FinPosSmartSdk.getInstance(activity);
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
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
