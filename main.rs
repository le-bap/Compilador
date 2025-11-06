use std::io;
fn main() {
let mut x=0;
while x<9{
println!("{}",x);
x=x+1;
if x==4 {
//@comentario@
break}}
println!("{}","aqui vem o for");
println!("{}","digite um numero");
let mut num2 = String::new();
io::stdin().read_line(&mut num2).expect("Falha ao ler a entrada");
let num2: f64 = num2.trim().parse().expect("Valor inválido");
for y in 2..=5 {
if y==num2 {
println!("{}",y);
break}}
println!("{}","aqui vem o do while");
let mut z=0;
loop{println!("{}","teste");
z=z+1;
if z>5{
break
}
}}