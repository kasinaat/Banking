if [ $1 == "" ]; then
  echo "Usage bash run.sh <input_file_relative_path>"
else
java -jar ./Banking.jar $1
fi