


use std::io;
fn main() {
for x in 0..=10 {
println!("{}","escreva o numero");
let mut num = String::new();
io::stdin().read_line(&mut num).expect("Falha ao ler a entrada");
println!("{}","seu numero eh ");
println!("{}",num);
}
}
