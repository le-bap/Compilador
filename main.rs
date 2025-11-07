use std::io;
fn main() {
let mut escolha=0;
while escolha!=3{
println!("{}","Escolha uma opcao:");
println!("{}","1 - Dizer Ola");
println!("{}","2 - Contar ate 5");
println!("{}","3 - Sair");
let mut escolha = String::new();
io::stdin().read_line(&mut escolha).expect("Falha ao ler a entrada");
let mut escolha: i32 = escolha.trim().parse().expect("Valor inválido");
if escolha==1 {
println!("{}","Ola, cozinheiro!");
}else {if escolha==2 {
for i in 1..=5 {
println!("{}",i);
}
}else {if escolha==3 {
println!("{}","Saindo...");
break}else {println!("{}","Opcao invalida!");
}}}}
}