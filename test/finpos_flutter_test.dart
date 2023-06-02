import 'package:flutter_test/flutter_test.dart';
import 'package:finpos_flutter/finpos_flutter.dart';
import 'package:finpos_flutter/finpos_flutter_platform_interface.dart';
import 'package:finpos_flutter/finpos_flutter_method_channel.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

class MockFinposFlutterPlatform
    with MockPlatformInterfaceMixin
    implements FinposFlutterPlatform {

  @override
  Future<String?> getPlatformVersion() => Future.value('42');
}

void main() {
  final FinposFlutterPlatform initialPlatform = FinposFlutterPlatform.instance;

  test('$MethodChannelFinposFlutter is the default instance', () {
    expect(initialPlatform, isInstanceOf<MethodChannelFinposFlutter>());
  });

  test('getPlatformVersion', () async {
    FinposFlutter finposFlutterPlugin = FinposFlutter();
    MockFinposFlutterPlatform fakePlatform = MockFinposFlutterPlatform();
    FinposFlutterPlatform.instance = fakePlatform;

    expect(await finposFlutterPlugin.getPlatformVersion(), '42');
  });
}
