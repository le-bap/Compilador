use std::io;
fn main() {
let mut limite: i32;
println!("{}","Digite um num limite");
let mut limite = String::new();
io::stdin().read_line(&mut limite).expect("Falha ao ler a entrada");
let mut limite: i32 = limite.trim().parse().expect("Valor inválido");
println!("{}","Contando de 1 ate o limite");
for i in 1..=limite {
if i%5==0 {
println!("{}","Numero multiplo de 5");
println!("{}",i);
}else {println!("{}",i);
}}
println!("{}","agora vamos fazer um menu interativo");
let mut comando= String::new();
while 1==1{
println!("{}","digite um comando (info, parar ou somar)");
let mut comando = String::new();
io::stdin().read_line(&mut comando).expect("Falha ao ler a entrada");
let mut comando = comando.trim().to_string();
if comando==String::from("info") {
println!("{}","sabia q o ceu eh azul");
}else {if comando==String::from("somar") {
println!("{}","digite o primeiro numero decimal");
let mut num1: f64;
let mut num1 = String::new();
io::stdin().read_line(&mut num1).expect("Falha ao ler a entrada");
let mut num1: f64 = num1.trim().parse().expect("Valor inválido");
println!("{}","digite o segundo numero");
let mut num2: f64;
let mut num2 = String::new();
io::stdin().read_line(&mut num2).expect("Falha ao ler a entrada");
let mut num2: f64 = num2.trim().parse().expect("Valor inválido");
let mut result=num1+num2;
println!("{}",result);
}else {if comando==String::from("parar") {
println!("{}","parando..");
break}else {println!("{}","comando invalido");
}}}}
}