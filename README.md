## Инструкции по запуску
1. Создать database.properties в [папке](https://github.com/paradisensei/HelpWeb/tree/master/model/src/main/resources/properties) модуля **model**
2. Создать googleapi.properties в [папке](https://github.com/paradisensei/HelpWeb/tree/master/service/src/main/resources) модуля **service**
3. Накатить схему базы данных командой `mvn clean package -P ddl_migration`
4. Наполнить базу данных тестовыми данными командой `mvn install -P dml_migration`
5. Развернуть собранный war файл в любимом веб-сервере