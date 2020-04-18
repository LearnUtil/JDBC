export HOME_DIR=$(dirname $0)
export POSTGRES_INIT_FILE=$HOME_DIR/data-dictionary.sql
export POSTGRES_USERNAME=postgres
export POSTGRES_DATABASE=trydb


psql -U ${POSTGRES_USERNAME} -c "create database $POSTGRES_DATABASE" -d "postgres"
psql -U ${POSTGRES_USERNAME} -f ${POSTGRES_INIT_FILE} -d ${POSTGRES_DATABASE}
