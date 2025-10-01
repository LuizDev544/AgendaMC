
function criarBotaoEditar(linha) {
    const btn = document.createElement('button');
    btn.textContent = 'Editar';
    btn.className = 'btn btn-warning btn-sm';
    btn.addEventListener('click', () => {
        const id = linha.dataset.id;
        
        document.getElementById('inputId').value = id;
        document.getElementById('inputNome').value = linha.children[1].textContent;
        document.getElementById('inputDescricao').value = linha.children[2].textContent;
        document.getElementById('inputData').value = linha.children[3].textContent;
        document.getElementById('inputLocal').value = linha.children[4].textContent;
        document.getElementById('inputPreco').value = linha.children[5].textContent;
        document.getElementById('inputCapacidade').value = linha.children[6].textContent;
        document.getElementById('inputTipo').value = linha.children[7].textContent;
        document.getElementById('inputApresentador').value = linha.children[8].textContent;
        document.getElementById('inputDuracao').value = linha.children[9].textContent;

       
        document.getElementById('formEdicao').style.display = 'block';
    });
    return btn;
}


async function carregarEventos() {
    try {
        const res = await fetch('http://localhost:8080/api/admin/eventos', {
            headers: {
                "Authorization": "Basic " + btoa("admin:senha123")
            }
        });
        const eventos = await res.json();

        const tbody = document.querySelector('#tabelaEventos tbody');
        tbody.innerHTML = '';

        eventos.forEach(e => {
            const tr = document.createElement('tr');
            tr.dataset.id = e.id;
            tr.innerHTML = `
                <td>${e.id}</td>
                <td>${e.nomeEvento}</td>
                <td>${e.descricaoDoEvento || ''}</td>
                <td>${e.dataDoEvento}</td>
                <td>${e.localDoEvento}</td>
                <td>${e.precoDoEvento || ''}</td>
                <td>${e.capacidadeDePessoasNoEvento || ''}</td>
                <td>${e.tipoDoEvento || ''}</td>
                <td>${e.apresentadorDoEvento || ''}</td>
                <td>${e.duracaoDoEvento || ''}</td>
                <td></td>
            `;
            tr.querySelector('td:last-child').appendChild(criarBotaoEditar(tr));
            tbody.appendChild(tr);
        });
    } catch (err) {
        console.error('Erro ao carregar eventos:', err);
    }
}


document.getElementById("formEdicao").addEventListener("submit", async (e) => {
    e.preventDefault();
    const id = document.getElementById("inputId").value;
    const eventoAtualizado = {
        nomeEvento: document.getElementById("inputNome").value,
        descricaoDoEvento: document.getElementById("inputDescricao").value,
        dataDoEvento: document.getElementById("inputData").value,
        localDoEvento: document.getElementById("inputLocal").value,
        precoDoEvento: parseFloat(document.getElementById("inputPreco").value),
        capacidadeDePessoasNoEvento: parseInt(document.getElementById("inputCapacidade").value),
        tipoDoEvento: document.getElementById("inputTipo").value,
        apresentadorDoEvento: document.getElementById("inputApresentador").value,
        duracaoDoEvento: document.getElementById("inputDuracao").value
    };

    try {
        const res = await fetch(`http://localhost:8080/api/admin/eventos/${id}`, {
            method: "PUT",
            headers: {
            "Content-Type": "application/json",
            "Authorization": "Basic " + btoa("admin:senha123")
            },
            body: JSON.stringify(eventoAtualizado)
        });

        if (!res.ok) throw new Error("Erro ao atualizar evento");

        document.getElementById("msgEdicao").innerText = "Evento atualizado com sucesso!";
        document.getElementById("formEdicao").style.display = "none";
        carregarEventos(); 
    } catch (err) {
        console.error(err);
        document.getElementById("msgEdicao").innerText = "Falha ao atualizar evento!";
    }
});


document.addEventListener("DOMContentLoaded", () => {
    carregarEventos();
});
