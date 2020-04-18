export HOME_DIR=$(dirname $0)/bin
export JARS_PATH=$HOME_DIR/jars
export SRC_PATH=$HOME_DIR/src
export CLASSPATH=$HOME_DIR/class
export MAIN_CLASS=com.jdbc.TestUtil.TestUtil


javac -cp $JARS_PATH/postgresql-42.2.5.jar -d $CLASSPATH $SRC_PATH/*/*.java
java -cp $CLASSPATH:$JARS_PATH/postgresql-42.2.5.jar $MAIN_CLASS
