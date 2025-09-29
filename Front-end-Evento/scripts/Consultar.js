function mostrarEvento(evento){
    document.querySelector('txtnome').value         = evento.nome;
    document.querySelector('txtdescricao').value    = evento.descricao;
    document.querySelector('txtdataevento').value   = evento.dataevento;
    document.querySelector('txtlocal').value        = evento.local;
    document.querySelector('txtpreco').value        = evento.preco;
    document.querySelector('txtcapacidade').value   = evento.capacidade;
    document.querySelector('txttipo').value         = evento.tipo;
    document.querySelector('txtapresentador').value = evento.apresentador;
    document.querySelector('txtduracao').value      = evento.duracao;
}

async function consultarEvento() {
    const idevento = document.querySelector('#idevento').value;
    if (idevento.length < 1){
        alert("Id Inválido , insira outro novamente");
        return
    }
    const url = `http://localhost:8080/eventos/list/${idevento}`;
    const dados = await fetch(url);
    if(dados.status === 200){
        const evento = await dados.json();
        mostrarEvento(product);   
    }else {
        alert("Id: " + idevento + " não encontrado");
    }
}