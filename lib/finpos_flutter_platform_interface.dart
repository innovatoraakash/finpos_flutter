import 'package:plugin_platform_interface/plugin_platform_interface.dart';

import 'finpos_flutter_method_channel.dart';

abstract class FinposFlutterPlatform extends PlatformInterface {
  /// Constructs a FinposFlutterPlatform.
  FinposFlutterPlatform() : super(token: _token);

  static final Object _token = Object();

  static FinposFlutterPlatform _instance = MethodChannelFinposFlutter();

  /// The default instance of [FinposFlutterPlatform] to use.
  ///
  /// Defaults to [MethodChannelFinposFlutter].
  static FinposFlutterPlatform get instance => _instance;

  /// Platform-specific implementations should set this with their own
  /// platform-specific class that extends [FinposFlutterPlatform] when
  /// they register themselves.
  static set instance(FinposFlutterPlatform instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<String?> getPlatformVersion() {
    throw UnimplementedError('platformVersion() has not been implemented.');
  }
}
