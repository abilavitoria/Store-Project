let carrinho = [];

async function comprar(){
    const id = document.getElementById("id_produto").value;
    const quantidade = document.getElementById("qtd_produto").value;
    const token = sessionStorage.getItem('token');

    if(!id) return alert("Informe o id do produto");

    try{
        const response = await fetch(`http://localhost:8081/produtos/${id}`, {
            headers: {'Authorization': `Bearer ${token}`}
        });

        if(response.ok){
            const produto = await response.json();

            const item = {
                produto:{id: produto.id},
                nome: produto.nome,
                quantidade: quantidade,
                precoUnitario: produto.preco
            };

            carrinho.push(item);
            atualizarTabela();
        }else{
            alert("Produto não encontrado!");
        }
    }catch(error){
        console.error(error);
    }
}

function atualizarTabela(){
    const tbody = document.querySelector("#tabela-itens tbody");
    const total = document.getElementById("valor-total");
    tbody.innerHTML = "";
    let totalGeral = 0;

    carrinho.forEach((item, index) => {
        const subtotal = item.quantidade * item.precoUnitario;
        totalGeral += subtotal;
        tbody.innerHTML += `
            <tr>
                <td>${item.produto.id}</td>
                <td>${item.nome}</td>
                <td>${item.quantidade}</td>
                <td>${item.precoUnitario.toFixed(2)}</td>
                <td>${subtotal.toFixed(2)}</td>
            </tr>
        `;
    });
    total.innerText = totalGeral.toFixed(2);
}

async function finalizarVenda(){
    const token = sessionStorage.getItem('token');

    const dadosVenda = {
        descricao: document.getElementById("venda-descricao").value,
        cliente: {id: document.getElementById("cliente_id").value},
        itens: carrinho
    };

    if(carrinho.length === 0)return alert("O carrinho está vazio!");

    try{
        const response = await fetch('http://localhost:8081/vendas', {
            method:'POST',
            headers:{ 
                'Content-Type':'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(dadosVenda)
        });

        if(response.ok){
            alert("Venda realizada com sucesso!");
            window.location.reload();
        }else{
            alert("Eroo ao finalizar venda");
        }
    }catch(error){
        console.error(error);
    }
}