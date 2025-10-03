document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("loginForm");
    const msg = document.getElementById("msg");

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
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({ email, senha })
            });

            console.log("Status da Resposta:", response.status);

            if (!response.ok) {
                throw new Error("Falha no login. Verifique suas credenciais.");
            }

            const data = await response.json();
            console.log("Dados recebidos JWT:", data);

            if (data.authenticated && data.token) {
                localStorage.setItem('jwtToken', data.token);
                localStorage.setItem('userData', JSON.stringify({
                    usuario: data.usuario,
                    role: data.role
                }));

                msg.textContent = "✅ Login realizado com sucesso!";
                msg.style.color = "green";
                

                setTimeout(() => {
                    window.location.href = "PainelADM.html";
                }, 1000);
                
            } else {
                msg.textContent = "❌ Credenciais inválidas!";
                msg.style.color = "red";
            }
        } catch (err) {
            console.error("Erro no login:", err);
            msg.textContent = "Erro de conexão com o servidor.";
            msg.style.color = "red";
        }
    });
});

function limparTokens() {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('userData');
    console.log("Tokens limpos manualmente");
}