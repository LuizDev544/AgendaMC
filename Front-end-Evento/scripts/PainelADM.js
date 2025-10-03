document.addEventListener("DOMContentLoaded", async () => {
    console.log("PainelADM.js carregado");
    
    try {
        const userData = JSON.parse(localStorage.getItem('userData'));
        if (userData) {
            document.getElementById('userInfo').textContent = `Admin: ${userData.usuario}`;
        }
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
