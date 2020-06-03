/*
created		14/09/2017
modified		15/09/2017
project		
model		
company		
author			reinaldo
version		
database		mysql 5 
*/

create database janus;
use janus;
drop table if exists usu_professor;
drop table if exists disc_pro;
drop table if exists disciplina;
drop table if exists store;
drop table if exists answer;
drop table if exists question;
drop table if exists inventario;
drop table if exists item;
drop table if exists usuario;



create table usuario (
	usu_id int not null auto_increment,
	usu_nome varchar(51) not null,
	usu_senha varchar(31) not null,
	usu_email varchar(51) not null,
	usu_nasc date not null,
	usu_login varchar(20) not null,
	usu_coins int not null default 0,
	usu_checkpoint int not null default 0,
	usu_perm tinyint not null default 0,
	unique (usu_id),
 primary key (usu_id)) engine = myisam;

create table item (
	item_id int not null auto_increment,
	item_nome varchar(50),
	item_desc varchar(100),
	item_tipo int not null,
	item_efeito int not null,
	unique (item_id),
 primary key (item_id)) engine = myisam;

create table inventario (
	invent_id int not null auto_increment,
	slot int,
	item_quant int,
	item_id int,
	usu_id int not null,
	unique (invent_id),
 primary key (invent_id)) engine = myisam;

create table question (
	quest_id int not null auto_increment,
	disc_id char(20) not null,
	quest_desc varchar(255) not null,
	nivel int not null,
	unique (quest_id),
 primary key (quest_id)) engine = myisam;

create table answer (
	answer_id int not null auto_increment,
	quest_id int not null,
	answer_correct bit(1) not null,
	answer_desc varchar(255) not null,
	unique (answer_id),
 primary key (answer_id)) engine = myisam;

create table store (
	store_id int not null auto_increment,
	usu_id int not null,
	quest_id int not null,
	acertou bit(1) not null,
	unique (store_id),
 primary key (store_id)) engine = myisam;

create table disciplina (
	disc_id char(20) not null,
	disc_nome varchar(60) not null,
 primary key (disc_id)) engine = myisam;

create table disc_pro (
	disc_id char(20) not null,
	id char(20) not null,
 primary key (disc_id)) engine = myisam;

create table usu_professor (
	usu_id int not null,
	pro_id int not null,
	id char(20) not null,
 primary key (id)) engine = myisam;


alter table inventario add foreign key (usu_id) references usuario (usu_id) on delete  restrict on update  restrict;
alter table store add foreign key (usu_id) references usuario (usu_id) on delete  restrict on update  restrict;
alter table usu_professor add foreign key (usu_id) references usuario (usu_id) on delete  restrict on update  restrict;
alter table usu_professor add foreign key (pro_id) references usuario (usu_id) on delete  restrict on update  restrict;
alter table inventario add foreign key (item_id) references item (item_id) on delete  restrict on update  restrict;
alter table store add foreign key (quest_id) references question (quest_id) on delete  restrict on update  restrict;
alter table answer add foreign key (quest_id) references question (quest_id) on delete  restrict on update  restrict;
alter table question add foreign key (disc_id) references disciplina (disc_id) on delete  restrict on update  restrict;
alter table disc_pro add foreign key (disc_id) references disciplina (disc_id) on delete  restrict on update  restrict;
alter table disc_pro add foreign key (id) references usu_professor (id) on delete  restrict on update  restrict;


/* users permissions */

insert into question(disc_id,quest_desc,nivel)values(1,'1+1=',1);
insert into answer(quest_id,answer_correct,answer_desc)values(1,1,'2'),(1,0,'3'),(1,0,'infinito'),(1,0,'15');

insert into question(disc_id,quest_desc,nivel)values(1,'1x1=',1);
insert into answer(quest_id,answer_correct,answer_desc)values(2,1,'1'),(2,0,'3'),(2,0,'infinito'),(2,0,'15');

insert into question(disc_id,quest_desc,nivel)values(1,'1-1=',1);
insert into answer(quest_id,answer_correct,answer_desc)values(3,1,'0'),(3,0,'3'),(3,0,'infinito'),(3,0,'15');

insert into question(disc_id,quest_desc,nivel)values(1,'7-1=',1);
insert into answer(quest_id,answer_correct,answer_desc)values(4,1,'6'),(4,0,'3'),(4,0,'infinito'),(4,0,'15');

insert into question(disc_id,quest_desc,nivel)values(1,'infinito+infinito=',1);
insert into answer(quest_id,answer_correct,answer_desc)values(5,0,'6'),(5,0,'3'),(5,1,'infinito'),(5,0,'15');

insert into question(disc_id,quest_desc,nivel)values(1,'Qual é a raiz quadrada de 225?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(6,1,'15'),(6,0,'25'),(6,0,'35'),(6,0,'20');

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 60 ao quadrado?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(7,1,'3600'),(7,0,'360'),(7,0,'390'),(7,0,'36');

insert into question(disc_id,quest_desc,nivel)values(1,'Mariana tem 18 anos. Sua irmã mais velha Melissa tem o triplo de sua idade. Quantos anos tem melissa?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(8,1,'54'),(8,0,'62'),(8,0,'58'),(8,0,'56');

insert into question(disc_id,quest_desc,nivel)values(1,'Maria tinha 24 balas e deu 1/3 para sua prima. Com quantas balas Maria ficou?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(9,1,'8'),(9,0,'4'),(9,0,'12'),(9,0,'16');

insert into question(disc_id,quest_desc,nivel)values(1,'João fez uma prova de matemática de 50 questões e apenas acertou 24% delas. Quantas questões ele acertou?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(10,1,'12'),(10,0,'6'),(10,0,'18'),(10,0,'24');

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 3 ao cubo?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(11,1,'27'),(11,0,'32'),(11,0,'9'),(11,0,'12');

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 5a+8-3a=a+24?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(12, 1, '16'), (12, 0, '10'),(12, 0,'22'),(12, 0,'15'); 

insert into question(disc_id,quest_desc,nivel)values(1,' Quanto é 2c-c+7c=-2c+100?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 13, 1, ' 10'), ( 13, 0, ' 16'),( 13, 0,' 22'),( 13, 0,' 8'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 6b-10+2b=06?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 14, 1, '2'), ( 14, 0, '5'),( 14, 0,'10'),( 14, 0,' 8'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 2e+12-e=-2e+24?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 15, 1, '4'), ( 15, 0, '5'),( 15, 0,'8'),( 15, 0,'2'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 3x+100=2x+100+200?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 16, 1, '200'), ( 16, 0, '155'),( 16, 0,'210'),( 16, 0,'100'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é x+2+3=11?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 17, 1, '6'), ( 17, 0, '5'),( 17, 0,'8'),( 17, 0,'10'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 5g-2g+10=25?',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 18, 1, '5'), ( 18, 0, '6'),( 18, 0,'4'),( 18, 0,'10'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 6h-2h+20=36',1);
insert into answer(quest_id,answer_correct,answer_desc)values( 19, 1, '4'), ( 19, 0, '6'),( 19, 0,'5'),( 19, 0,'8'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é x-5=10?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(20, 1, '15'), (20, 0, '10'),(20, 0,'5'),(20, 0,'2'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é 4k-6=2k+8?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(21, 1, '7'), (21, 0, '12'),(21, 0,'5'),(21, 0,'14'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quantos números 1 eu tenho de 1 até 191?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(22, 1, '132'), (22, 0, '146'),(22, 0,'136'),(22, 0,'125'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é o resultado dessa conta 6+2x(4-3)?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(23, 1, '8'), (23, 0, '24'),(23, 0,'12'),(23, 0,'6'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é o dobro 1500?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(24, 1, '3000'), (24, 0, '4000'),(24, 0,'5000'),(24, 0,'2500'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação x+30 = 40?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(25, 1, '10'), (25, 0, '15'),(25, 0,'5'),(25, 0,'20'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 30-20+2x=10?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(26, 1, '0'), (26, 0, '15'),(26, 0,'10'),(26, 0,'20'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual o resultado da equaçao 3x-10+13=-2x+28?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(27, 1, '5'), (27, 0, '15'),(27, 0,'25'),(27, 0,'20'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 20x-30=40+30-20?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(28, 1, '4'), (28, 0, '8'),(28, 0,'16'),(28, 0,'2'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação -5x+45-89=-90+41?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(29, 1, '1'), (29, 0, '8'),(29, 0,'4'),(29, 0,'2'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 10x-20=40+50?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(30, 1, '11'), (30, 0, '8'),(30, 0,'16'),(30, 0,'6'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 20-80+2x=10?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(31, 1, '35'), (31, 0, '23'),(31, 0,'12'),(31, 0,'30'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 19+2x-13=10-20?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(32, 1, '-8'), (32, 0, '-15'),(32, 0,'6'),(32, 0,'13'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 10x-12+10=+18-20 ?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(33, 1, '0'), (33, 0, '2'),(33, 0,'-8'),(33, 0,'13'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Qual a resposta da equação 13x-23-45=-7x+12',1);
insert into answer(quest_id,answer_correct,answer_desc)values(34, 1, '4'), (34, 0, '2'),(34, 0,'0'),(34, 0,'8'); 

insert into question(disc_id,quest_desc,nivel)values(1,'Quanto é (12/4) / (3/5) ?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(35, 1, '5'), (35, 0, '10'),(35, 0,'0'),(35, 0,'18'); 

insert into question(disc_id,quest_desc,nivel)values(1,'No lançamento de um dado não viciado, qual é a probabilidade de obtermos um número maior que 4?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(36, 1, '1/3'), (36, 0, '1/2'),(36, 0,'1/4'),(36, 0,'1/5');

insert into question(disc_id,quest_desc,nivel)values(1,'Num estacionamento vazio existem 40 vagas numeradas de 1 a 40. Qual é a probabilidade do primeiro motorista que chegar estacionar numa vaga par ou de número maior que 10?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(37, 1, ' 35/40'), (37, 0, ' 30/40'),(37, 0,' 25/40'),(37, 0,' 20/40');

insert into question(disc_id,quest_desc,nivel)values(1,'Um barril cheio de água pesa 40 quilos. Se retirarmos metade da água nele contida, pesará 22 quilos. Quanto pesa o barril vazio?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(38, 1, '4 quilos'), (38, 0, '18 quilos '),(38, 0,'8 quilos '),(38, 0,'2 quilos');

insert into question(disc_id,quest_desc,nivel)values(1,'Uma escola recebeu a doação de 3 caixas de 1 000 livros, mais 8 caixas de 100 livros, mais 5 pacotes de 10 livros, mais 9 livros. Esta escola recebeu',1);
insert into answer(quest_id,answer_correct,answer_desc)values(39, 1, '3859'), (39, 0, '3859'),(39, 0,'30859'),(39, 0,'38590');

insert into question(disc_id,quest_desc,nivel)values(1,' Fernando tem, no seu cofrinho, cinco moedas de R$ 0,05, oito moedas de R$ 0,10 e três moedas de R$ 0,25. Que quantia Fernando tem no cofrinho?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(40, 1, 'RS 1,80'), (40, 0, 'RS 1,55'),(40, 0,'RS 2,05'),(40, 0,'RS 4,05');

insert into question(disc_id,quest_desc,nivel)values(1,'O Circo “Los Pampas” anuncia que o espetáculo vai começar às 15h 20min e terá duração de 2 horas e 30 minutos. Então a que horas vai terminar o espetáculo do circo?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(42, 1, '17h50min'), (42, 0, '17h10min'),(42, 0,'17h20min'),(42, 0,'17h30min');

insert into question(disc_id,quest_desc,nivel)values(1,' Um dia tem 24 horas, 1 hora tem 60 minutos e 1 minuto tem 60 segundos. Que fração da hora corresponde a 35 minutos?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(43, 1, ' 7/12'), (43, 0, '7/4 '),(43, 0,'35/24'),(43, 0,'60/35');

insert into question(disc_id,quest_desc,nivel)values(1,' Em um jogo de vôlei, os torcedores estavam acomodados em três áreas distintas do ginásio, demarcadas por cores diferentes. Na área verde havia 21 828 torcedores, na azul 12 100 e na amarela 32 072. Nesse jogo, apenas 20% do total de torcedores presentes no ginásio torciam pelo time que venceu a partida. Qual é o número de torcedores que torciam pelo time vencedor?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(44, 1, ' 13200'), (44, 0, ' 2420'),(44, 0,'4365'),(44, 0,'6414');

insert into question(disc_id,quest_desc,nivel)values(1,'Uma torneira com problemas continua pingando mesmo depois de fechada,desperdiçando em uma hora 125 mL de água. Quantos litros de água desperdiçará em 24 horas?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(45, 1, '3,0'), (45, 0, '1,5'),(45, 0,'30,0'),(45, 0,'15,0');

insert into question(disc_id,quest_desc,nivel)values(1,'Antônio, Bernardo, Cláudio e Daniel elaboraram juntos uma prova de 40 questões, tendo recebido por ela um total de R$ 2.200,00. Os três primeiros fizeram o mesmo número de questões e Daniel fez o dobro do que fez cada um dos outros. Se o dinheiro deve ser repartido proporcionalmente ao trabalho de cada um, Daniel deverá receber uma quantia, em reais, igual a: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(46, 1, '880,00'), (46, 0, '800,00'),(46, 0,'850,00'),(46, 0,'820,00');

insert into question(disc_id,quest_desc,nivel)values(1,' João gasta 1/3 do seu salário no aluguel do apartamento onde mora e 2/5 do que lhe sobra em alimentação, ficando com R$ 480,00 para as demais despesas. Portanto, o salário de João é igual a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(47, 1, 'R$ 1.200,00; '), (47, 0, 'R$ 1.500,00; '),(47, 0,'R$ 1.800,00; '),(47, 0,' R$ 2.100,00;');

insert into question(disc_id,quest_desc,nivel)values(1,' Em um treino de basquete, um jogador ganha 5 pontos por cada cesta que acerta e perde 3 pontos por cada cesta que erra. Em 10 tentativas, um jogador obteve 26 pontos. Logo, o número de cestas que ele acertou foi: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(48, 1, '7'), (48, 0, '4'),(48, 0,'6'),(48, 0,'5');

insert into question(disc_id,quest_desc,nivel)values(1,' Em uma padaria compra-se 1 bisnaga e 1 litro de leite por R$ 1,50 e 2 bisnagas e 3 litros de leite por R$ 3,90. Então, 2 bisnagas e 1 litro de leite custarão: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(49, 1, ' R$ 2,10;'), (49, 0, ' R$ 2,20; '),(49, 0,' R$ 2,30; '),(49, 0,' R$ 2,40;');

insert into question(disc_id,quest_desc,nivel)values(1,'Um cofre contém apenas anéis e brincos, de ouro ou de prata. Sabe-se que 80% dos anéis são de prata e 10% das jóias são brincos. A porcentagem de jóias desse cofre que são anéis de ouro é: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(50, 1, '18%; '), (50, 0, '90%;'),(50, 0,'63%; '),(50, 0,'10%. ');

insert into question(disc_id,quest_desc,nivel)values(1,'Após serem efetuados os débitos de R$ 48,30, R$ 27,00 e R$ 106,50 e os créditos de R$ 200,00 e R$ 350,00, o saldo da conta bancária de uma pessoa passou para R$1.040,90. Logo, antes dessas operações, o saldo dessa conta era de: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(51, 1, 'R$ 672,70; '), (51, 0, 'R$ 731,70; '),(51, 0,'R$ 672,70; '),(51, 0,'R$ 1.772,70. ');

insert into question(disc_id,quest_desc,nivel)values(1,' Para arrumar 120 salas, 2 pessoas gastam 5 dias. Se precisamos que as salas sejam arrumadas em um único dia, será necessário contratar mais n pessoas que trabalhem no mesmo ritmo das duas iniciais. O valor de n é:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(52, 1, ' 8'), (52, 0, ' 11'),(52, 0,' 6'),(52, 0,' 13');

insert into question(disc_id,quest_desc,nivel)values(1,'O resultado da adição ( 2/3 ) + (-7/2) é igual a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(53, 1, 'n.d.a. '), (53, 0, '-17/3'),(53, 0,'17/6'),(53, 0,'6/17');

insert into question(disc_id,quest_desc,nivel)values(1,' O resultado da multiplicação (- 4/5 ) x (-7/2) é igual a: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(54, 1, ' 2,8'), (54, 0, '-2,8'),(54, 0,'-2,4'),(54, 0,'2');

insert into question(disc_id,quest_desc,nivel)values(1,' O resultado da divisão (- 0,5) : (-3/6) é igual a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(55, 1, ' 1'), (55, 0, ' -1'),(55, 0,'15/6'),(55, 0,'2/3');

insert into question(disc_id,quest_desc,nivel)values(1,' O resultado da potenciação [ (- 4/9)3 ] 5 é igual a: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(56, 1, 'n.d.a'), (56, 0, ' (4/9)15 '),(56, 0,' (- 4/9)8 '),(56, 0,' (-12/9)5 ');

insert into question(disc_id,quest_desc,nivel)values(1,' O m. d. c. (máximo divisor comum) dos números naturais 60, 40 e 24 é igual a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(57, 1, ' n.d.a'), (57, 0, ' 20'),(57, 0,'40'),(57, 0,'14');

insert into question(disc_id,quest_desc,nivel)values(1,'O resultado da subtração 29,57 - 45,678 é igual a: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(58, 1, ' - 16,108 '), (58, 0, 'n.d.a'),(58, 0,'16,108'),(58, 0,'1,6108');
insert into question(disc_id,quest_desc,nivel)values(1,' O valor da expressão {[ ( 0,9)2 - (3,8)2] : (-1/4)}, no universo dos números racionais, é igual a: ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(59, 1, ' 54,52'), (59, 0, ' 54,50'),(59, 0,' 50,54'),(59, 0,' -54,50');
insert into question(disc_id,quest_desc,nivel)values(1,' O conjunto verdade da equação (x -1) = (6 - 2x), no universo dos números racionais, é igual a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(60, 1, ' V={ 7/3 }'), (60, 0, ' V={-3/7 } '),(60, 0,' V={-7/3 } '),(60, 0,' V={3/7 } ');
insert into question(disc_id,quest_desc,nivel)values(1,' Efetuando-se 20802 - 10192 obtém-se um número compreendido entre ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(61, 1, ' 10000 e 20000 '), (61, 0, ' 6000 e 10000 '),(61, 0,' 3000 e 6000 '),(61, 0,' 1000 e 3000 ');
insert into question(disc_id,quest_desc,nivel)values(1,'Uma pessoa, ao efetuar a multiplicação de um número inteiro x por 296, achou o produto 39960. Ao conferir o resultado percebeu que havia se enganado, trocando em x as posições do algarismo das unidades com o das dezenas. Nessas condições, o produto correto deveria ser ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(62, 1, '45288'), (62, 0, '42828 '),(62, 0,'43248 '),(62, 0,' 45126 ');
insert into question(disc_id,quest_desc,nivel)values(1,' No almoxarifado de certa empresa há uma pilha de folhas de papel, todas com 0,25mm de espessura. Se a altura da pilha é de 1,80m, o número de folhas empilhadas é ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(63, 1, ' 7200'), (63, 0, ' 4500 '),(63, 0,' 720'),(63, 0,'72');
insert into question(disc_id,quest_desc,nivel)values(1,' Em uma empresa, o atendimento ao público é feito por 45 funcionários que se revezam, mantendo a relação de 3 homens para 2 mulheres. É correto afirmar que, nessa empresa, dão atendimento',1);
insert into answer(quest_id,answer_correct,answer_desc)values(64, 1, '  18 mulheres. '), (64, 0, ' 25 homens. '),(64, 0,' 16 mulheres.'),(64, 0,' 18 homens.');
insert into question(disc_id,quest_desc,nivel)values(1,' Os salários de dois técnicos judiciários, X e Y, estão entre si assim como 3 está para 4. Se o dobro do salário de X menos a metade do salário de Y corresponde a R$ 720,00, então os salários dos dois totalizam ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(65, 1, ' R$ 1260,00'), (65, 0, ' R$ 1200,00 '),(65, 0,' R$ 1300,00 '),(65, 0,' R$ 1360,00 ');
insert into question(disc_id,quest_desc,nivel)values(1,' Três técnicos judiciários arquivaram um total de 382 processos, em quantidades inversamente proporcionais às suas respectivas idades: 28, 32 e 36 anos. Nessas condições, é correto afirmar que o número de processos arquivados pelo mais velho foi ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(66, 1, ' 112'), (66, 0, '126'),(66, 0,'144'),(66, 0,'152');
insert into question(disc_id,quest_desc,nivel)values(1,' Quatro funcionários de uma empresa são capazes de atender, em média, 52 pessoas por hora. Diante disso, espera-se que seis funcionários, com a mesma capacidade operacional dos primeiros, sejam capazes de atender por hora uma média de ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(67, 1, ' 78 pessoas'), (67, 0, '75 pessoas'),(67, 0,' 72 pessoas'),(67, 0,' 82 pessoas');
insert into question(disc_id,quest_desc,nivel)values(1,' Paco fundou uma empresa com R$ 20 000,00 de capital e, após 4 meses, admitiu Capo como sócio, que ingressou com o capital de R$ 32 000,00. Se após 1 ano de atividades a empresa gerou um lucro de R$ 19840,00, então Paco recebeu ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(68, 1, ' R$ 640,00 a menos que Capo.'), (68, 0, ' R$ 640,00 a mais que Capo'),(68, 0,' R$ 580,00 a menos que Capo. '),(68, 0,'  R$ 580,00 a mais que Capo');
insert into question(disc_id,quest_desc,nivel)values(1,'Se o valor de um certo artigo era R$ 780,00 e, após um ano, era R$ 624,00, a taxa anual de desvalorização foi de ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(69, 1, '0% '), (69, 0, '21% '),(69, 0,'24%'),(69, 0,'18% ');
insert into question(disc_id,quest_desc,nivel)values(1,'Para emitir uma ordem de pagamento, um Banco cobra de seus clientes uma comissão de 1,8% sobre o seu valor. Se, ao enviar por esse Banco uma ordem de pagamento, um cliente desembolsou o total de R$ 5 090,00, o valor dessa ordem de pagamento era de ',1);
insert into answer(quest_id,answer_correct,answer_desc)values(70, 1, 'R$ 5000,00 '), (70, 0, 'R$ 4800,00 '),(70, 0,'R$ 4750,00 '),(70, 0,'R$ 4600,00 ');
insert into question(disc_id,quest_desc,nivel)values(1,' No primeiro trimestre do ano passado, o vertedouro (canal de segurança que controla o nível de água) de um lago localizado no Parque da Aclamação, na capital paulista, se rompeu. Em 50 minutos, 780.000 litros de água escoaram, deixando o lago praticamente seco. Em média, quantos litros de água escoaram do lago a cada segundo?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(71, 1, ' 260'), (71, 0, '180'),(71, 0,'156'),(71, 0,'260');
insert into question(disc_id,quest_desc,nivel)values(1,'Na sucessão dos números inteiros que são múltiplos de 5: (...; -10; 5; 0; 5; 10;...) determine dois números consecutivos, cuja soma seja 475.',1);
insert into answer(quest_id,answer_correct,answer_desc)values(72, 1, '235 e 250'), (72, 0, '245 e 250'),(72, 0,'225 e 230'),(72, 0,'240 e 245');
insert into question(disc_id,quest_desc,nivel)values(1,' No final do verão, uma loja de roupas ofereceu 20% de desconto em todas as peças. Uma pessoa, ao comprar uma camisa de R$36,00, recebeu, em reais, um desconto de',1);
insert into answer(quest_id,answer_correct,answer_desc)values(73, 1, ' 7,20'), (73, 0, '6,20'),(73, 0,'3,60'),(73, 0,'8,60');
insert into question(disc_id,quest_desc,nivel)values(1,' Um tanque de combustível tem 0,8 m³ de volume interno. Quantas latas de 16 litros são necessárias para encher esse tanque até 70% de sua capacidade?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(74, 1, ' 35'), (74, 0, '25'),(74, 0,'50'),(74, 0,'40');
insert into question(disc_id,quest_desc,nivel)values(1,'Duas pessoas tem juntas 90 anos. Subtraindo-se 15 anos da idade da mais velha e acrescentando-se os mesmos 15 anos da idade da mais jovem, as idades ficam iguais. Qual a idade de cada pessoa, respectivamente?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(75, 1, '60;30'), (75, 0, '20; 70.'),(75, 0,'40; 50.'),(75, 0,'80; 10.');
insert into question(disc_id,quest_desc,nivel)values(1,' Na área de serviço de uma residência deverá ser construída uma parede de 2m x 8m. Quantos tijolos deverão ser comprados, sabendo-se que em cada m² serão usados 20 tijolos?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(76, 1, ' 320'), (76, 0, '160 '),(76, 0,'180'),(76, 0,'240');
insert into question(disc_id,quest_desc,nivel)values(1,' Uma caixa contém 60 balas. Quantas dezenas de balas há na caixa?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(77, 1, '6'), (77, 0, '10 '),(77, 0,'4'),(77, 0,'8');
insert into question(disc_id,quest_desc,nivel)values(1,' Numa mesa retangular, seu comprimento é igual ao triplo da largura. Se o comprimento dessa mesa é 3,60 m, então o perímetro é',1);
insert into answer(quest_id,answer_correct,answer_desc)values(78, 1, ' 9,6 m'), (78, 0, ' 2,40 m'),(78, 0,' 4,80 m'),(78, 0,' 7,20 m');
insert into question(disc_id,quest_desc,nivel)values(1,'O perímetro de um triângulo é 96 cm, sendo seus lados proporcionais aos números 3, 4 e 5. Então, o menor lado do triângulo mede:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(79, 1, '24 cm'), (79, 0, '40 cm'),(79, 0,'28 cm'),(79, 0,'32 cm');
insert into question(disc_id,quest_desc,nivel)values(1,' Foi feita uma pesquisa entre os 450 funcionários de uma empresa. O resultado apontou que 60% dos funcionários são mulheres e que 45% dos funcionários homens tem menos de 30 anos. Analisando esses dados, concluímos que nesta empresa trabalham:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(80, 1, ' 99 homens que tem 30 anos ou mais.'), (80, 0, ' 81 homens que tem 30 anos ou mais'),(80, 0,' 121 mulheres que tem menos de 30 anos.'),(80, 0,' 149 mulheres que tem menos de 30 anos.');
insert into question(disc_id,quest_desc,nivel)values(1,' No período de um ano, uma cidade com 200.000 habitantes apresentou uma taxa de crescimento de 0,5 %. Se a taxa se mantiver por mais um ano, o aumento total da população nesses dois anos corresponderá a:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(81, 1, ' 2005 habitantes'), (81, 0, ' 2015 habitantes'),(81, 0,' 2105 habitantes'),(81, 0,' 2115 habitantes');
insert into question(disc_id,quest_desc,nivel)values(1,' José nasceu em 1917 e veio para o Rio com 20 anos. Em que ano José chegou ao Rio?',1);
insert into answer(quest_id,answer_correct,answer_desc)values(82, 1, ' 1937'), (82, 0, ' 1897'),(82, 0,' 1927'),(82, 0,' 1957');
insert into question(disc_id,quest_desc,nivel)values(1,' Um certo homem obteve o montante de R$ 5.000,00 aplicando R$ 4.000,00 por cinco meses. Podemos afirmar que a taxa de juros nessa transação foi de',1);
insert into answer(quest_id,answer_correct,answer_desc)values(83, 1, ' 5 % a.m'), (83, 0, ' 2 % a.m.'),(83, 0,' 8,5 % a.m.'),(83, 0,' 12 % a.m.');
insert into question(disc_id,quest_desc,nivel)values(1,' Ao caminhar 100 m, uma mulher dá, em média, 120 passos.',1);
insert into answer(quest_id,answer_correct,answer_desc)values(84, 1, ' 900'), (84, 0, '750'),(84, 0,'450'),(84, 0,'225');
insert into question(disc_id,quest_desc,nivel)values(1,' Francisco tinha certa quantia de figurinhas. Ele deu um quarto a seu irmão, 3 a seu amigo e, do restante, tirou um quarto para trocar, ficando com 9 figurinhas. O número de figurinhas que Francisco tinha era de:',1);
insert into answer(quest_id,answer_correct,answer_desc)values(85, 1, ' 20'), (85, 0, '16'),(85, 0,'24'),(85, 0,'32');


insert into question(quest_id, disc_id, quest_desc, nivel) values (9000, 1, 'imagem 1', 1);
insert into question(quest_id, disc_id, quest_desc, nivel) values (9001, 1, 'imagem 2', 1);
insert into question(quest_id, disc_id, quest_desc, nivel) values (9002, 1, 'imagem 3', 1);


insert into usuario(usu_nome, usu_senha, usu_email, usu_nasc, usu_login, usu_coins, usu_checkpoint, usu_perm)values('Admin', 'eggjlrr2017', 'contatojanusgame@gmail.com', '2017-01-01', 'admin', 0, 0, 3);
insert into usuario(usu_nome, usu_senha, usu_email, usu_nasc, usu_login, usu_coins, usu_checkpoint)values('rei', 'abc', 'rei@hotmail', '2000-04-02', 'rei', 0, 0);
insert into usuario(usu_nome, usu_senha, usu_email, usu_nasc, usu_login, usu_coins, usu_checkpoint)values('Toxico', '123', 'hiiiiii@hotmail', '2000-07-08', 'hiiiiii', 0, 0);



insert into item(item_nome, item_desc, item_tipo, item_efeito) values ('Poção média', 'Cura 30 de vida', 1, 30); 