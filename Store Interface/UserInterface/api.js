const API_URL = "http://localhost:8081";

async function buscarClientes(){
    const response = await fetch(`${API_URL}/clientes`);
    return await response.json();
}

async function buscarProdutos() {
    const response = await fetch(`${API_URL}/produtos`);
    return await response.json();
}

async function finalizarVenda(vendaData) {
    const response = await fetch(`${API_URL}/vendas`, {
        method: 'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(vendaData)
    });
    return response.ok;
}