document.addEventListener("DOMContentLoaded", async () => {
    console.log("PainelADM.js carregado");
    
    try {
        // Carregar dados do usuário
        const userData = JSON.parse(localStorage.getItem('userData'));
        if (userData) {
            document.getElementById('userInfo').textContent = `Admin: ${userData.usuario}`;
        }

        // Carregar dados do dashboard
        await carregarDashboard();
        
    } catch (error) {
        console.error("Erro ao carregar painel:", error);
    }
});

async function carregarDashboard() {
    try {
        const token = localStorage.getItem('jwtToken');
        
        // Carregar eventos
        const response = await fetch("http://localhost:8080/api/admin/eventos", {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const eventos = await response.json();
            atualizarDashboard(eventos);
        } else {
            console.error("Erro ao carregar eventos:", response.status);
        }
    } catch (error) {
        console.error("Erro ao carregar dashboard:", error);
    }
}

function atualizarDashboard(eventos) {
    // Atualizar estatísticas
    const totalEventos = eventos.length;
    const eventosFuturos = eventos.filter(evento => 
        new Date(evento.dataDoEvento) >= new Date()
    ).length;
    
    const receitaEstimada = eventos.reduce((total, evento) => 
        total + (evento.precoDoEvento * evento.capacidadeDePessoasNoEvento), 0
    );

    document.getElementById('totalEventos').textContent = totalEventos;
    document.getElementById('eventosFuturos').textContent = eventosFuturos;
    document.getElementById('receitaEstimada').textContent = 
        `R$ ${receitaEstimada.toLocaleString('pt-BR', { minimumFractionDigits: 2 })}`;

    // Atualizar lista de eventos
    const listaEventos = document.getElementById('listaEventos');
    if (eventos.length > 0) {
        listaEventos.innerHTML = eventos.slice(0, 5).map(evento => `
            <div class="event-item">
                <h4>${evento.nomeEvento}</h4>
                <p>📅 ${new Date(evento.dataDoEvento).toLocaleDateString('pt-BR')}</p>
                <p>📍 ${evento.localDoEvento}</p>
                <p>💰 R$ ${evento.precoDoEvento?.toFixed(2) || '0.00'}</p>
            </div>
        `).join('');
    } else {
        listaEventos.innerHTML = '<p>Nenhum evento cadastrado.</p>';
    }
}