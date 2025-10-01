document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");

    if (!form) {
        console.error("Formulário de login não encontrado!");
        return;
    }

    form.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = document.getElementById("email").value;
        const senha = document.getElementById("senha").value;

        try {
            const response = await fetch("http://localhost:8080/auth/login", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, senha }),
                credentials: "include" 
            });

            const data = await response.json();

            // 📢 PASSO 3 DE VERIFICAÇÃO: Veja o valor exato no console.
            console.log("Status da Resposta:", response.status);
            console.log("Role recebida do servidor:", data.role);


            if (response.ok) {
                alert(data.message);

                // 1. Converte a role para maiúscula (para evitar problemas de caixa)
                const userRole = data.role ? data.role.toUpperCase() : null;

                // 2. Lógica de Redirecionamento 
                if (userRole === "ADMIN") {
                    // ✅ REDIRECIONAMENTO EXCLUSIVO PARA ADMIN
                    window.location.href = "PainelADM.html";
                } else {
                    // ✅ Redirecionamento para qualquer outro usuário (ou caso a role seja nula)
                    window.location.href = "PainelUsuario.html";
                }

            } else {
                alert("Erro no login: " + data.message);
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert("Falha de conexão com o servidor. Verifique a URL.");
        }
    });
});