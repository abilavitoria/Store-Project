const API_URL = "http://localhost:8081/produtos";
const token = localStorage.getItem('token');

document.addEventListener('DOMContentLoaded', listarProdutos);

async function listarProdutos() {
    const response = await fetch(API_URL, {
        headers: {'Authorization': `Bearer ${token}`}
    });

    if(response.ok){
        const produtos = await response.json();
        const tbody = document.querySelector("#tabela-produtos tbody");
        tbody.innerHTML = produtos.map(p => `
            <tr>
                <td>#${p.id}</td>
                <td style= "color: var(--accent)">${p.nome}</td>
                <td>R$ ${p.preco.toFixed(2)}</td>
                <td>${p.quantidade}</td>
                <td><button class="btn-del" onclick="deletarProduto(${p.id})">Remover</button></td>
            </tr>
            `).join('');
    }
}

document.getElementById("produto-form").addEventListener('submit', async (evento) =>{
    evento.preventDefault();

    const dados = {
        nome: document.getElementById("nome").value,
        descricao: document.getElementById("descricao").value,
        preco: document.getElementById("preco").value,
        quantidade: document.getElementById("quantidade").value
    };

    const response = await fetch(API_URL, {
        method: 'POST',
        headers:{'Content-Type':'application/json', "Authorization":`Bearer ${token}`},
        body: JSON.stringify(dados)
    });

    if(response.ok){
        listarProdutos();
        evento.target.reset();
    }else{
        alert("Erro ao cadastrar verifique suas permissões");
    }
});

async function deletarProduto(id){
    if(confirm("Remover item do estoque?")){
        await fetch(`${API_URL}/${id}`, {
            method: 'DELETE',
            headers:{'Authorization': `Bearer ${token}`}
        });
        listarProdutos();
    }
}

function logout(){
    localStorage.removeItem('token');
    window.location.href = "main.html";
}