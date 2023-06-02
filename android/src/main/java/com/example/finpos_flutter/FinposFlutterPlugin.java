package com.example.finpos_flutter;

import androidx.annotation.NonNull;

import global.citytech.payment.sdk.api.PaymentService;
import global.citytech.payment.sdk.api.PosPaymentApi;
import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.embedding.engine.plugins.activity.ActivityAware;

import global.citytech.payment.sdk.utils.StringUtils;
import global.citytech.finpossmart.print.PrintHelper;
import global.citytech.finpossmart.sdk.FinPosSmartSdk;
import global.citytech.finpossmart.sdk.api.SmartSdk;

/** FinposFlutterPlugin */
public class FinposFlutterPlugin implements FlutterPlugin, MethodCallHandler, ActivityAware   {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private  var context= Context;
  private  var activity= Activity;
  private MethodChannel channel;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "finpos_flutter");
    channel.setMethodCallHandler(this);
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
        SmartSdk smartSdk;
      PaymentService paymentService ;
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
   else if (call.method.equals("posPaymentSdkInitialize")) {
         paymentService = PosPaymentApi.getInstance( context,
                posPaymentConfiguration );
//        smartSdk = FinPosSmartSdk.getInstance(context);
      result.success("Android iniialization success" );
    }
   else if (call.method.equals("finPosSdkInitialize")) {
    smartSdk = FinPosSmartSdk.getInstance(context);
//        smartSdk = FinPosSmartSdk.getInstance(context);
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }
    
    else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
