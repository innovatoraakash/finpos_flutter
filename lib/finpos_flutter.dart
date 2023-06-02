
import 'finpos_flutter_platform_interface.dart';

class FinposFlutter {
  Future<String?> getPlatformVersion() {
    return FinposFlutterPlatform.instance.getPlatformVersion();
  }
}
