@echo off
IF "%1" == "" goto NO_FILE_END
java.exe -jar .\Banking.jar %1

:NO_FILE_END
echo "Usage run.bat <input_file_relative_path>"