let carrinho = [];

async function comprar(){
    const id = document.getElementById("id_produto").value;
    const quantidade = document.getElementById("qtd_produto").value;
    const token = sessionStorage.getItem('token');

    if(!id) return alert("Informe o id do produto");
    if(parseInt(quantidade) <= 0)return alert("A quantidade deve ser maior que zero")

    try{
        const response = await fetch(`http://localhost:8081/produtos/${id}`, {
            headers: {'Authorization': `Bearer ${token}`}
        });

        if(response.ok){
            const produto = await response.json();

            const item = {
                produtoId: produto.id,
                nome: produto.nome,
                quantidade: parseInt(quantidade),
                precoUnitario: produto.preco
            };

            carrinho.push(item);
            atualizarTabela();

            document.getElementById("id_produto").value = "";
            document.getElementById("quantidade").value = "1";
        }else{
            alert("Produto não encontrado!");
        }
    }catch(error){
        console.error("Erro na busca do produto:", error);
    }
}

function atualizarTabela(){
    const tbody = document.querySelector("#tabela-itens tbody");
    const total = document.getElementById("valor-total");
    tbody.innerHTML = "";
    let totalGeral = 0;

    carrinho.forEach((item) => {
        const subtotal = item.quantidade * item.precoUnitario;
        totalGeral += subtotal;
        tbody.innerHTML += `
            <tr>
                <td>${item.produtoId}</td>
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
    const idClienteInput = document.getElementById("cliente_id").value;

    if(!idClienteInput) return alert("Informe o id do cliente!");
    if(carrinho.length === 0) return alert("O carrinho está vazio!");

    const dadosVenda = {
        descricao: document.getElementById("venda-descricao").value,
        clienteId: parseInt(idClienteInput),
        itens: carrinho.map(item => ({
            produtoId: item.produtoId,
            quantidade: item.quantidade
        }))
    };

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
            carrinho = [];
            window.location.reload();
        }else{
            const message = await response.text();
            alert("Erro ao finalizar venda" + message);
        }
    }catch(error){
        console.error("Erro na requisição POST", error);
        alert("Erro na conexão com o servidor");
    }
}