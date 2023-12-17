insert into pollutant_type(id,pollutant_type_name) values(1,'за викиди в атмосферне повітря забруднюючих речовин стаціонарними  джерелами забруднення');
insert into pollutant_type(id,pollutant_type_name) values(2,'за скиди забруднюючих речовин у водні об`єкти');
insert into pollutant_type(id,pollutant_type_name) values(3,'за розміщення відходів');
insert into pollutant_type(id,pollutant_type_name) values(4,'за утворення радіоактивних відходів');
insert into pollutant_type(id,pollutant_type_name) values(5,'за тимчасове зберігання радіоактивних відходів їх виробниками понад  установлений особливими умовами ліцензії строк');

insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(1,'SO2',500,0.05,5000,0.08,0.05,1,18413.24);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(2,'NO2',500,0.04,5000,0.04,2.04,1,2574.43);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(3,'CO',250,3,5000,0,0.05,1,96.99);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(4,'Тверді',50,0.15,500,0.05,0.001,1,96.99);

insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(5,'SO2',500,0.05,5000,0.08,0.05,2,978777.84);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(6,'NO2',500,0.04,5000,0.04,2.04,2,168741.52);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(7,'CO',250,3,5000,0,0.05,2,17173.04);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(8,'Тверді',50,0.15,500,0.05,0.001,2,3437.76);

insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(9,'SO2',500,0.05,5000,0.08,0.05,3,1546.22);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(10,'NO2',500,0.04,5000,0.04,2.04,3,56.32);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(11,'CO',250,3,5000,0,0.05,3,1546.22);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(12,'Тверді',50,0.15,500,0.05,0.001,3,56.32);

insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(13,'Плутоній-239',500,0.05,5000,0.08,0.05,4,50);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(14,'Цезій-137',500,0.04,5000,0.04,2.04,4,2);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(15,'Рандон-222',250,3,5000,0,0.05,4,2);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(16,'Калій-40',50,0.15,500,0.05,0.001,4,2);

insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(17,'Плутоній-239',500,0.05,5000,0.08,0.05,5,21084.66);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(18,'Цезій-137',500,0.04,5000,0.04,2.04,5,4216.92);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(19,'Рандон-222',250,3,5000,0,0.05,5,4216.92);
insert into pollutants (id,name,elv,tlv,mfr,rfc,sf,pollutant_type_id,tax_rate) values(20,'Калій-40',50,0.15,500,0.05,0.001,5,4216.92);