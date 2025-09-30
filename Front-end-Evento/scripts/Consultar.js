function mostrarEvento(evento){
    document.querySelector('#txtnome').value = evento.nomeEvento || 'num tem ;-;';
    document.querySelector('#txtdescricao').value = evento.descricaoDoEvento || '';
    document.querySelector('#txtdataevento').value = evento.dataDoEvento ? evento.dataDoEvento.split('T')[0] : '';
    document.querySelector('#txtlocal').value = evento.localDoEvento || '';
    document.querySelector('#txtpreco').value = evento.precoDoEvento != null ? evento.precoDoEvento : '';
    document.querySelector('#txtcapacidade').value = evento.capacidadeDePessoasNoEvento != null ? evento.capacidadeDePessoasNoEvento : '';
    document.querySelector('#txttipo').value = evento.tipoDoEvento || '';
    document.querySelector('#txtapresentador').value = evento.apresentadorDoEvento || '';
    document.querySelector('#txtduracao').value = evento.duracaoDoEvento ? evento.duracaoDoEvento.substring(0,5) : '';
}

async function consultarEvento() {
    const id = document.querySelector('#idevento').value.trim();
    if (id.length < 1){
        alert("Id Inválido , insira outro novamente");
        return;
    }

    const url = `http://localhost:8080/api/public/eventos/${id}`;
    
    try {
        const resposta = await fetch(url, {
            method: 'GET',
            headers: {
                'Authorization': 'Basic ' + btoa('admin:senha123') 
            }
        });

        if(resposta.status === 200){
            const evento = await resposta.json();
            mostrarEvento(evento);   
        } else {
            alert("Id: " + id + " não encontrado");
        }

    } catch (erro) {
        console.error(erro);
    }
}
