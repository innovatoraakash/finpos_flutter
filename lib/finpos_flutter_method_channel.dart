import 'package:flutter/foundation.dart';
import 'package:flutter/services.dart';

import 'finpos_flutter_platform_interface.dart';

/// An implementation of [FinposFlutterPlatform] that uses method channels.
class MethodChannelFinposFlutter extends FinposFlutterPlatform {
  /// The method channel used to interact with the native platform.
  @visibleForTesting
  final methodChannel = const MethodChannel('finpos_flutter');

  @override
  Future<String?> getPlatformVersion() async {
    final version = await methodChannel.invokeMethod<String>('getPlatformVersion');
    return version;
  }
}
