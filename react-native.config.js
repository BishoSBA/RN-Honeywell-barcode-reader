/**
 * @type {import('@react-native-community/cli-types').UserDependencyConfig}
 */
module.exports = {
  dependency: {
    platforms: {
      android: {
        sourceDir: '../android',
        packageImportPath:
          'import com.honeywell.HoneywellBarcodeReaderPackage;',
        libraryName: 'react_codegen_RNHoneywellSpec',
        cmakeListsPath: 'generated/jni/CMakeLists.txt',
      },
    },
  },
};
