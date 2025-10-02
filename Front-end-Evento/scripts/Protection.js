// Protection.js - Versão melhorada
async function protegerRota(roleRequerido = null) {
    try {
        const token = localStorage.getItem('jwtToken');
        
        if (!token) {
            console.log("❌ Proteção: Nenhum token encontrado");
            redirecionarParaLogin();
            return false;
        }

        const resp = await fetch("http://localhost:8080/auth/validate", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ token: token })
        });

        const data = await resp.json();
        
        if (!data.valid) {
            console.log("❌ Proteção: Token inválido");
            limparDadosUsuario();
            redirecionarParaLogin();
            return false;
        }

        // Verificar role específica se necessário
        if (roleRequerido && data.role !== roleRequerido) {
            console.log(`❌ Proteção: Role ${data.role} não tem acesso (requerido: ${roleRequerido})`);
            alert("❌ Você não tem permissão para acessar esta página.");
            window.location.href = "PainelUsuario.html";
            return false;
        }

        console.log("✅ Proteção: Acesso permitido para", data.usuario);
        return true;
        
    } catch (err) {
        console.error("Erro na proteção de rota:", err);
        limparDadosUsuario();
        redirecionarParaLogin();
        return false;
    }
}

function limparDadosUsuario() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userData');
}

function redirecionarParaLogin() {
    window.location.href = "Login.html";
}

// 🔧 NOVO: Proteção automática para páginas ADMIN
document.addEventListener('DOMContentLoaded', function() {
    // Verifica se estamos em uma página que requer autenticação
    const currentPage = window.location.pathname.split('/').pop();
    
    if (currentPage === 'CadastrarEvento.html' || 
        currentPage === 'PainelADM.html' || 
        currentPage === 'ExcluirEvento.html') {
        protegerRota('ROLE_ADMIN');
    }
});