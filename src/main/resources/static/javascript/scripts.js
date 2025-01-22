function mostrarOpcoes(){
    var divs = document.querySelectorAll(".opcao_reserva");
    divs.forEach((div) => {
        div.style.display = 'none'
    })

    var selected = document.querySelector('input[name="tipoReserva"]:checked');
    if (selected){
        var divId = selected.value;
        document.getElementById(divId).style.display = 'block';

        if (divId === 'equipamento'){
            document.getElementById('espaco_select').selectedIndex = 0;
        }

        if (divId === 'espaco'){
            document.getElementById('equipamento_select').selectedIndex = 0;
        }
    }
}
