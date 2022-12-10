//Пример команд для csv

//Ввод данных:
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Client insert 1 ClientName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  csv Type insert 1 TypeName Description 2
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Item insert 1 ItemName Description 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Provider insert 1 ProviderName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Selling insert 1 1 1 12 28.03.2020 19000 //совершить продажу
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Delivery insert 1 1 1 12.03.2020 20.03.2020 18 1 19000 //совершить поставку товаров

//обновление данных:

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Client update 1 NEWClientName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  csv Type update 1 NEWTypeName Description 2
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Item update 1 NEWItemName NEWDescription 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Provider update 1 NEWProviderName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Selling update 1 1 1 12 28.03.2020 200 //обновить данные о покупке
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Delivery update 1 1 1 12.03.2020 20.03.2020 7 1 11000 // изменить данные о поставке

//вывод записей

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv client select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Item select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Provider select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  csv Type select 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Selling select 1 // просмотреть информацию о покупке товара
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Delivery select 1 // просмотреть информацию о поставке товара


//удаление данных:

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Client delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Item delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Provider delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  csv Type delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Selling delete 1 // отменить покупку
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar csv Delivery delete 1 // отменить поставку




//Пример команд для xml

//Ввод данных:
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Client insert 1 ClientName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  xml Type insert 1 TypeName Description 2
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Item insert 1 ItemName Description 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Provider insert 1 ProviderName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Selling insert 1 1 1 12 28.03.2020 19000 //совершить продажу
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Delivery insert 1 1 1 12.03.2020 20.03.2020 18 1 19000 //совершить поставку товаров

//обновление данных:

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Client update 1 NEWClientName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  xml Type update 1 NEWTypeName Description 2
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Item update 1 NEWItemName NEWDescription 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Provider update 1 NEWProviderName 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Selling update 1 1 1 12 28.03.2020 200 //обновить данные о покупке
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Delivery update 1 1 1 12.03.2020 20.03.2020 7 1 11000 // изменить данные о поставке

//вывод записей

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml client select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Item select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Provider select 1 
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  xml Type select 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Selling select 1 // просмотреть информацию о покупке товара
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Delivery select 1 // просмотреть информацию о поставке товара


//удаление данных:

java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Client delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Item delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Provider delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar  xml Type delete 1
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Selling delete 1 // отменить покупку
java -Dlog4j2.configurationFile=log4j2.properties -DpropertyValue=enviroment.properties -jar productturnover.jar xml Delivery delete 1 // отменить поставку
