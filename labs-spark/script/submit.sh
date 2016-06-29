gradle jar
/home/alan/programs/scala/spark/bin/spark-submit \
  --class "hello.SimpleApp" \
  --master local[4] \
  /home/alan/ttx/gitlab/cybertrans/labs/cbt-labs-spark/build/libs/cbt-labs-spark-1.0.4.jar
