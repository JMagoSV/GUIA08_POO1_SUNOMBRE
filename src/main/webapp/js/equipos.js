$(document).ready(function () {
    
    $.fn.procesarPeticion = function() {
        var dataForm = $(this).closest('form').serializeArray(); //Obtener los inputs del form
        dataForm.push({ name: 'btonEqui', value: $(this).val() }); //Enviar el tipo de CRUD
        $.ajax({
            type: "POST",
            url: "EquiposServ",
            data: $.param(dataForm),
            dataType: "json", //La respuesta se captura con json
            success: function(data) {
                if(data.resp)
                {
                    if(data.limp) //Si es nuevo se limpia el form
                    {
                        $('form#formEqui')[0].reset();
                        $('form#formEqui').children('div.btn-group').html('<input type="submit" class="btn btn-default" name="btonEqui" value="Guardar"/>');
                    }
                    else //Si tiene data se llena el formulario
                    {
                        if(data.objeEqui !== null)
                        {
                            //Uso https://github.com/webarthur/jquery-fields
                            var form = $('form#formEqui').fieldValues();
                            form.codiEqui(data.objeEqui.codiEqui);
                            form.nombEqui(data.objeEqui.nombEqui);
                            form.descEqui(data.objeEqui.descEqui);
                            if(data.esNuevo)
                            {
                                $('#tablEqui').append('<tr class="odd"><td><input type="radio" name="codiEquiRadi"' +
                                                        'value="'+data.objeEqui.codiEqui+'"></td><td>'+data.objeEqui.descEqui+'</td><td>'+
                                                        data.objeEqui.nombEqui+'</td></tr>');
                            }
                        }
                        if(data.estaModi)
                        {
                            $('form#formEqui').children('div.btn-group').html('<input type="submit" class="btn btn-default" name="btonEqui" value="Nuevo"/>');
                            $('form#formEqui').children('div.btn-group').append('<input type="submit" class="btn btn-primary" name="btonEqui" value="Modificar"/>');
                            $('form#formEqui').children('div.btn-group').append('<input type="submit" class="btn btn-danger" name="btonEqui" value="Eliminar"/>');
                        }
                        else
                        {
                            $('form#formEqui').children('div.btn-group').html('<input type="submit" class="btn btn-default" name="btonEqui" value="Guardar"/>');
                        }
                    }
                    showMens('MESS_SUCC', 'Atención', data.mens);
                    iniciarEventos();
                }
                else
                {
                    showMens('MESS_ERRO', 'Atención', data.mens);
                }
            },
            error: function() {
                showMens('MESS_ERRO', 'Atención', 'Error al procesar la petición');
            }
        });
        return false;
    };
    iniciarEventos();    
});

function iniciarEventos()
{
    $('input[name="btonEqui"]').unbind('click').on('click', function (event) {
        $(this).procesarPeticion();
        event.preventDefault();
    });
}