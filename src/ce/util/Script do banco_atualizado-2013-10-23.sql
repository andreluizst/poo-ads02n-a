/*
create database estoque;
use estoque;
create table Categoria(
  CodCateg bigint not null auto_increment,
  Descricao varchar(20) not null,
  primary key(CodCateg)
  )engine = innoDB;
#select * from categoria
create table Unidade(
  codUnid int not null auto_increment primary key,
  descricao varchar(10)
  )engine=innoDB;
create table Produto(
  codProd bigint not null auto_increment,
  codCateg bigint not null,
  codUnid int not null,
  DescProd varchar(50),
  QtdeEstoq double default 0.00,
  qtdeMin double default 0.00,
  qtdeIdeal double default 0.00,
  Primary Key(codProd),
  constraint produto_fkcodUnid foreign key(codUnid) references unidade(codUnid),
  constraint produto_fkCodCateg foreign key(codCateg) references categoria(CodCateg)
  )
  engine = innoDB;
create table estados(
  UF char(2) not null primary key,
  Descricao varchar(50)
  ) engine=innoDB;
create table Fornecedor(
  codForn bigint not null auto_increment primary key,
  Nome varchar(50) not null,
  CNPJ varchar(14) not null unique,
  Logradouro varchar(50),
  Bairro varchar(30),
  Municipio varchar(30),
  UF char(2),
  cep varchar(8),
  fone varchar(12),
  email varchar(60),
  constraint fornecedor_fkuf foreign key(UF) references estados(UF)
  ) engine=innoDB;
create table FornXProd(
  codForn bigint not null,
  codProd bigint not null,
  constraint fornxprod_pk primary key(codForn, codProd),
  constraint fornxprod_fkCodForn foreign key(codForn) references fornecedor(codForn),
  constraint fornxprod_fkCodProd foreign key(codProd) references produto(codProd)
  ) engine=innoDB;
create table Perfil(
  codPerfil int not null auto_increment primary key,
  nome varchar(30)
  ) engine=innoDB;
create table Funcionario(
  CPF varchar(11) not null primary key,
  Nome varchar(50),
  dtNasc varchar(10),
  Logradouro varchar(50),
  Num int,
  Comp varchar(30),
  Bairro varchar(30),
  Municipio varchar(30),
  UF char(2) not null,
  CEP varchar(12),
  Fone varchar(15),
  Email varchar(50),
  constraint fucionario_fkUF foreign key(UF) references estados(UF)
  ) engine=innoDB;
create table Usuario(
  codUsr int not null auto_increment primary key,
  Nome varchar(20),
  codPerfil int not null,
  CPF varchar(11),
  senha varchar(15),
  constraint usuario_fkCodPerfil foreign key(codPerfil) references Perfil(codPerfil),
  constraint usuario_fkCpf foreign key(CPF) references Funcionario(CPF),
  constraint usuario_uCpf unique(CPF)
  ) engine=innoDB;
create table LocalEstoque(
  codLocal int not null auto_increment primary key,
  Descricao varchar(15) not null
  ) engine=innoDB;
create table Entrada(
  codEnt bigint not null auto_increment primary key,
  codProd bigint not null,
  codForn bigint not null,
  dataEnt date not null,
  lote varchar(15),
  qtde double not null,
  saldo double default 0,
  constraint entrada_fkCodProd foreign key(codProd) references produto(codProd),
  constraint entrada_fkCodForn foreign key(codForn) references fornecedor(codForn),
  constraint entrada_chkQtde check(qtde > 0)
  ) engine=innoDB;
create table Saida(
  codSaida bigint not null auto_increment primary key,
  codEnt bigint not null,
  dataSaida date not null,
  qtde double not null,
  constraint saida_chkQtde check(qtde > 0),
  constraint saida_fkCodEnt foreign key(codEnt) references Entrada(codEnt)
  ) engine=innoDB;
insert into estados values('AC', "Acre");
insert into estados values('AL', "Alagoas");
insert into estados values('AM', "Amazonas");
insert into estados values('AP', "Amapá");
insert into estados values('BA', "Bahia");
insert into estados values('CE', "Ceará");
insert into estados values('MA', "Maranhão");
insert into estados values('MG', "Minas Gerais");
insert into estados values('MS', "Mato Grosso do Sul");
insert into estados values('MT', "Mato Grosso");
insert into estados values('PA', "Pará");
insert into estados values('PB', "Paraíba");
insert into estados values('PE', "Pernambuco");
insert into estados values('PI', "Piauí");
insert into estados values('PR', "Paraná");
insert into estados values('RJ', "Rio de Janeiro");
insert into estados values('RN', "Rio Grande do Norte");
insert into estados values('RO', "Rondonia");
insert into estados values('RR', "Roraima");
insert into estados values('RS', "Rio Grande do Sul");
insert into estados values('SC', "Santa Catarina");
insert into estados values('SE', "Sergipe");
insert into estados values('SP', "São Paulo");
insert into estados values('TO', "Tocantins");
alter table produto add column statusProd int;
alter table fornecedor add column Num int;
alter table fornecedor add column Comp varchar(30);
select * from estados;
insert into Perfil(nome) values("Administrador");
insert into Perfil(nome) values("Geral");
select * from perfil;
insert into Funcionario(cpf, nome, uf) values("99922233300", "André Luiz", "PE");
select * from funcionario;
insert into usuario(nome, codPerfil, cpf, senha) values("andre", 1, "99922233300", "admin");
select * from usuario;
insert into unidade(descricao) values("kg");
insert into unidade(descricao) values("m");
insert into unidade(descricao) values("Unidade");
insert into produto(codCateg, codUnid, DescProd, QtdeEstoq, qtdeMin, qtdeIdeal, statusProd) 
values(2, 3, "HD 500 GB", 150, 70, 120, 0)
*/
/*update produto 
	set descProd = "Memória RAM DDR3 800 MHz",
	qtdeEstoq = 98, qtdeMin = 90, qtdeIdeal = 116, statusProd = 0 
	where codProd = 3*/
/*#update usuario set nome = "admin", senha="admin" where codUsr = 1;
#alter table funcionario add column fone varchar(15);
#alter table entrada drop column dtEnt;
#alter table entrada add column dataEnt date;
#alter table entrada add column saldo double default 0;
#alter table saida drop column dtSaida;
#alter table saida add column dataSaida date not null;*/
select * from saida;