# 🍳 Linguagem "Cozinha" — Projeto de Compiladores

Este projeto implementa uma linguagem fictícia inspirada na cozinha, onde comandos de programação são representados por termos culinários. A ideia é gerar um arquivo .rs (Rust) com a tradução da linguagem personalizada, para que assim o código escrito seja executado. 

---

### Expressões Regulares

| Função | Palavra-chave (Cozinha) | Tradução Tradicional |
|:--|:--:|:--|
| Início do programa | `receita` | — |
| Fim do programa | `pratopronto` | — |
| Tipo inteiro | `ingrediente` | `inteiro` |
| Tipo decimal | `tempero` | `decimal` |
| Tipo texto/string | `receitinha` | `texto` |
| Começar/terminar texto | `“”` | `“”` |
| Declaração de variável | usa o tipo acima | — |
| Atribuição | `=` | `:=` |
| Entrada (`input`) | `prove` | `leia` |
| Saída (`print`) | `sirva` | `escreva` |
| Estrutura condicional `if` | `deguste` | `se` |
| Estrutura condicional `else` | `tempere` | `senao` |
| Laço `while` | `cozinhe_enquanto` | `enquanto` |
| Laço `for` | `bata` | `para` |
| Laço `do...while` | `ferva… enquanto` | `faça enquanto` |
| Para laço | `parar` | `pare` |
| Parêntese esquerdo | `(` | `(` |
| Parêntese direito | `)` | `)` |
| Chave esquerda | `{` | `{` |
| Chave direita | `}` | `}` |
| Ponto e vírgula | `;` | `;` |
| Comentário de linha | `@ … @` | `//` |
| Sinal de mais | `+` | `+` |
| Sinal de menos | `-` | `-` |
| Sinal de multiplicação | `*` | `*` |
| Sinal de divisão | `/` | `/` |

---

###  Gramática Livre de Contexto (GLC)

```ebnf
id → [a-z]+ 
num → [0-9]+ | num ‘.’ num
string → ‘“’ id ‘“’

exp → exp operador_arit exp | ‘(’ exp ‘)’ | num | id
condicao → ‘(’ id operador_comp num ‘)’
condicaoBata → ‘(’ id ‘=’ num ‘;’ id operador_comp num ‘)’

operador_comp → ‘<’ | ‘>’ | ‘==’ | ‘<=’ | ‘>=’
operador_arit → ‘+’ | ‘-’ | ‘*’ | ‘/’

main → ‘receita’ codigo ‘pratopronto’

codigo → sentenca codigo | ε
sentenca → atribuir | declarar | print | input | ifs | while | for | do_while

ifs → ‘deguste’ condicao ‘{’ codigo ‘}’
     | ‘deguste’ condicao ‘{’ codigo ‘}’ ‘tempere’ ‘{’ codigo ‘}’

while → ‘cozinhe_enquanto’ condicao ‘{’ codigo ‘}’
for → ‘bata’ condicaoBata ‘{’ codigo ‘}’
do_while → ‘ferva’ ‘{’ codigo ‘}’ ‘cozinhe_enquanto’ condicao

atribuir → id ‘=’ exp ‘;’ | tipo id ‘=’ exp
declarar → tipo id ‘;’

tipo → “receitinha” | “tempero” | “ingrediente”

print → ‘sirva’ ‘(’ string ‘)’ 
       | ‘sirva’ ‘(’ ‘{’ id ‘}’ ‘)’ ‘;’

input → ‘prove’ ‘(’ “%i” id ‘)’ 
        | ‘prove’ ‘(’ “%t” id ‘)’ 
        | ‘prove’ ‘(’ “%r” id ‘)’
```

---

### INSTRUÇÕES PARA EXECUÇÃO DO CÓDIGO

Pré-requisitos e considerações:
- Para executar o código, é preciso ter Java e Rust instalado no computador.
- Rust aceita somente a codificação UTF-8. Não use caracteres especiais e acentos
no código.

1) Faça o download do repositório e extraia a pasta;
2) Em um terminal, entre na pasta onde o download foi feito e compile o código
utilizando javac *.java;
3) Pra rodar a IDE, basta digitar java IDECozinha;
4) Escreva seu código e clique em Traduzir para gerar o arquivo main.rs (Rust) - Você
também pode clicar em Árvore de Derivação para visualizar a mesma ou Lista de
Tokens para ver os tokens gerados;
5) Com o main.rs criado, feche a IDE e acesse o terminal. Digite rustc main.rs para
compilar o código e depois ./main.rs para executá-lo.


## Exemplo de Código

<div style="display: flex; gap: 20px;">

<div style="flex: 1;">

### Código em Cozinha

```cozinha
receita
ingrediente max = 10;
sirva("digite um numero");
ingrediente x;
prove("%i" x);
deguste (x <= max) {
    sirva("max eh menor ou igual a 5");
}
tempere {
    ingrediente num_maior;
    sirva("digite um numero maior que 5");
    prove("%i" num_maior);
}
pratopronto
```

</div> <div style="flex: 1;">
  
### Código traduzido para Rust
  
```rust
use std::io;
fn main() {
    let mut max = 10;
    println!("{}","digite um numero");
    let mut x: i32;
    let mut x = String::new();
    io::stdin().read_line(&mut x).expect("Falha ao ler a entrada");
    let mut x: i32 = x.trim().parse().expect("Valor inválido");

    if x <= max {
        println!("{}","max eh menor ou igual a 5");
    } else {
        let mut num_maior: i32;
        println!("{}","digite um numero maior que 5");
        let mut num_maior = String::new();
        io::stdin().read_line(&mut num_maior).expect("Falha ao ler a entrada");
        let mut num_maior: i32 = num_maior.trim().parse().expect("Valor inválido");
    }
}
``` 



