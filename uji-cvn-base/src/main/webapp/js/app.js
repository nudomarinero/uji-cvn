function getUrlParams()
{
    var parameters = new Array();

    var pairs = window.location.search.slice(1).split('&');

    for ( var i = 0; i < pairs.length; i++)
    {
        var pair = pairs[i].split('=');
        if (pair[0] != "")
            parameters[pair[0]] = pair[1];
    }

    return parameters;
}

function getCookie(name)
{
    var cname = name + "=";
    var dc = document.cookie;
    if (dc.length > 0)
    {
        begin = dc.indexOf(cname);
        if (begin != -1)
        {
            begin += cname.length;
            end = dc.indexOf(";", begin);
            if (end == -1)
                end = dc.length;
            return unescape(dc.substring(begin, end));
        }
    }
    return '';
}

function getCodeLang(lang)
{
    var code = '';

    if (lang == 'ca')
    {
        code = 'cat';
    }
    else if (lang == 'es')
    {
        code = 'spa';
    }
    else if (lang == 'en')
    {
        code = 'eng';
    }

    return code;
}

$(document).ready(function()
{
    $(document).foundationTooltips();
    $(document).foundationCustomForms();

    var adminPage = false;

    if (window.location.href.indexOf('admin') != -1)
    {
        adminPage = true;
    }

    if ($('#consulta-cvn').length > 0 && $('#lista-consulta-cvn tbody tr').length > 0 && $('#lista-consulta-cvn tbody tr a').length == 0)
    {
        var urlImagen = "/cvn/images/loading.gif";
        var height = $('#boton-generar-cvn').height();
        $('#boton-generar-cvn').html($('#boton-generar-cvn').text() + ' <img src="' + urlImagen + '" />');
        $('#boton-generar-cvn').height(height);
        $('#boton-generar-cvn').css('backgroundColor', '#d0d0d0');

        $('#consulta-alert-refresh').show();

        setTimeout(function()
        {
            console.log('reload');
            location.reload();
        }, 30000);
    }

    $('#boton-generar-cvn').click(function()
    {
        if ($('#lista-consulta-cvn tbody tr').length > 0 && $('#lista-consulta-cvn tbody tr a').length == 0)
        {
            if (!confirm($('#generar-cvn-confirm').val()))
            {
                return false;
            }
        }

        var urlImagen = "/cvn/images/loading.gif";
        var boton = this;
        $(this).html($(this).text() + ' <img src="' + urlImagen + '" />');
        $(this).height($('#boton-generar-cvn').height());
        $(this).css('backgroundColor', '#d0d0d0');

        var params = getUrlParams();
        var idioma = '';
        if (params['idioma'])
        {
            idioma = getCodeLang(params['idioma']);
        }
        if (!params['idioma'] || idioma == '')
        {
            idioma = getCodeLang(getCookie('uji-lang'));
        }

        var plantilla = $('select[name=plantilla-cvn]').val();

        $.ajax(
        {
            type : 'GET',
            url : boton.href + "?plantilla=" + plantilla + "&lang=" + idioma,
            async : true
        }).always(function()
        {
            alert("S'ha solicitat la generació del CVN. En breu el tindràs disponible en aquesta mateixa pàgina")
            location.reload();
        });

        return false;
    });

    $('#boton-limpiar-cvn').click(function()
    {
        var boton = this;
        $(this).html($(this).text() + ' <img src="/cvn/images/loading.gif" />');
        $(this).height($('#boton-limpiar-cvn').height());
        $(this).css('backgroundColor', '#d0d0d0');

        $.ajax(
        {
            type : 'DELETE',
            url : boton.href,
            async : true
        }).always(function()
        {
            location.reload();
        });

        return false;
    });

    $('#boton-generar-cvn-admin').click(function()
    {
        var usuarios = $('#consulta-form-textarea').val();
        usuarios = usuarios.replace(/\r\n/g, '\n');
        usuarios = usuarios.replace(/\r/g, '\n');
        usuarios = usuarios.replace(/\n\s*\n/g, '\n');

        var listaUsuarios = usuarios.split('\n');
        var usuariosInvalidos = new Array();

        var pattern = new RegExp('^[1-9][0-9]*$');

        for ( var i = 0; i < listaUsuarios.length; i++)
        {
            var usuario = listaUsuarios[i].trim();
            if (!pattern.test(usuario))
            {
                usuariosInvalidos.push(usuario);
            }
        }

        if (usuariosInvalidos.length > 0)
        {
            $('#consulta-form-textarea').addClass("error");

            var error = $('#consulta-form-error').text();
            var ind = error.indexOf(':');
            error = error.substring(0, ind + 1);
            error = error + " " + usuariosInvalidos.join(", ");
            $('#consulta-form-error').text(error);
            $('#consulta-form-error').show();

            return false;
        }

        $('#consulta-form-error').hide();
        $('#consulta-form-textarea').removeClass("error");

        var boton = this;
        $(this).html($(this).text() + ' <img src="/cvn/images/loading.gif" />');
        $(this).height($('#boton-generar-cvn-admin').height());
        $(this).css('backgroundColor', '#d0d0d0');

        var params = getUrlParams();
        var idioma = '';
        if (params['idioma'])
        {
            idioma = getCodeLang(params['idioma']);
        }
        if (!params['idioma'] || idioma == '')
        {
            idioma = getCodeLang(getCookie('uji-lang'));
        }

        var plantilla = $('select[name=plantilla-cvn]').val();

        $.ajax(
        {
            type : 'POST',
            url : boton.href + "?plantilla=" + plantilla + "&lang=" + idioma,
            async : true,
            data : "usuarios=" + usuarios
        }).always(function()
        {
            location.reload();
        });

        return false;
    });

    $('.boton-borrar-plantilla').click(function()
    {
        var boton = this;

        $.ajax(
        {
            type : 'DELETE',
            url : boton.href,
            async : true
        }).success(function()
        {
            location.reload();
        }).error(function()
        {
            $('#borrar-plantilla-error').show();
        });

        return false;
    });

    $('.alert-box a.close').click(function()
    {
        $(this).closest(".alert-box").fadeOut();
        return false;
    });

    if ($('select[name=plantilla-cvn]'))
    {
       // Melia y Roger var url = $('#appPath').attr('href') + "plantillas/list";

        var url = "/cvn/rest/plantillas/list";


        $.ajax(
        {
            type : 'GET',
            url : url,
            async : true,
            dataType : 'json'
        }).success(function(data, textStatus, jqXHR)
        {
            $.each(data.data, function(i, plantilla)
            {
                $('select[name=plantilla-cvn]').append($('<option>',
                {
                    value : plantilla.id,
                    text : plantilla.nombre
                }));
            });
        });

           }

    if ($('#form-plantillas'))
    {
        var params = getUrlParams();

        if (params['plantillaId'] != null && params['plantillaId'] != "")
        {
            // Obtenemos los datos de la plantilla

            var url = $('#appPath').attr('href') + "plantillas/" + params['plantillaId'];

            $.ajax(
            {
                type : 'GET',
                url : url,
                async : true,
                dataType : 'json'
            }).success(function(data, textStatus, jqXHR)
            {
                rellenarFormularioPlantillas(data);
            }).fail(function(jqXHR, textStatus, errorThrown)
            {
                $("#form-plantillas-error-cargar").show();
            });

        }
        else
        {
            $('input:radio[name=radio-idiomas][value=cat]').prop('checked', true);
            $('.item input:checkbox').prop('checked', true);
            $('.form-opciones input:radio[value=all]').prop('checked', true);
        }

        $('#boton-plantillas-marcar').click(function()
        {
            marcarTodoFormPlantillas();
            return false;
        });

        $('#boton-plantillas-desmarcar').click(function()
        {
            desmarcarTodoFormPlantillas();
            return false;
        });

        $('.form-opciones input:text').focus(function()
        {
            $(this).parent().parent().find('input:radio').prop('checked', true);
            $(this).parents('fieldset').find('input:text').removeClass('error');
        });

        $('.form-opciones input:text').focusout(function()
        {
            var value = $(this).val();
            if (value.trim() == '' || isNaN(parseInt(value)))
            {
                $(this).addClass('error');
            }
            else
            {
                $(this).removeClass('error');
            }
        });

        $('.item input:checkbox').click(function()
        {
            if ($(this).prop('checked') == true && $(this).parents('fieldset').find('input:radio:checked').length == 0)
            {
                $(this).parents('fieldset').find('input:radio[value=all]').prop('checked', true);
            }
        });

        $('.form-opciones input:radio').click(function()
        {
            $(this).parents('fieldset').find('input:text').removeClass('error');
            var value = $(this).parent().parent().find('input:text').val();
            if (value.trim() == '' || isNaN(parseInt(value)))
            {
                $(this).parent().parent().find('input:text').addClass('error');
            }
        });

        $('#form-plantillas input:text[name=nombre-plantilla]').focusout(function()
        {
            if ($(this).val().trim() == '')
            {
                $(this).addClass('error');
            }
            else
            {
                $(this).removeClass('error');
            }
        });

        $('#boton-plantillas-guardar').click(function()
        {
            $("#form-plantillas-error-guardar").hide();
            var errorForm = false;

            // Antes de enviar los datos, se valida el formulario
            var nombre = $('#form-plantillas input:text[name=nombre-plantilla]');
            if ($(nombre).val().trim() == '')
            {
                $(nombre).addClass('error');
                errorForm = true;
            }

            if ($('#form-plantillas-idiomas input:radio[name=radio-idiomas]:checked').length == 0)
            {
                errorForm = true;
            }

            $('#form-plantillas fieldset').each(function()
            {
                if ($(this).find('input:checkbox:checked').length > 0)
                {
                    if ($(this).find('input:radio:checked').length == 0)
                    {
                        errorForm = true;
                    }
                    else if ($(this).find('input:radio:checked').val() != 'all')
                    {
                        var radio = $(this).find('input:radio:checked');
                        var input = $(radio).parent('div').parent('div').find('input:text');

                        var value = $(input).val().trim();
                        if (value == '' || isNaN(parseInt(value)))
                        {
                            $(input).addClass('error');
                            errorForm = true;
                        }
                    }
                }
            });

            if (errorForm)
            {
                $('#form-plantillas-error').show();
            }
            else
            {
                $('#form-plantillas-error').hide();
                var plantilla = JSON.stringify(crearPlantillaFromFormulario());
                var url = this.href;

                var index = window.location.href.indexOf('/form');
                var urlDestino = window.location.href.substr(0, index);

                // Enviamos la petición
                $.ajax(
                {
                    type : 'POST',
                    url : url,
                    async : true,
                    data : plantilla,
                    contentType : 'application/json; charset=utf-8'
                }).success(function()
                {
                    window.location.href = urlDestino;
                }).fail(function(jqXHR, textStatus, errorThrown)
                {
                    $("#form-plantillas-error-guardar").show();
                });
            }

            return false;
        });
    }
});

function desmarcarTodoFormPlantillas()
{
    $('.form-opciones input:radio').prop('checked', false);
    $('.item input:checkbox').prop('checked', false);
    $('.form-opciones input:text.error').removeClass('error');
}

function marcarTodoFormPlantillas()
{
    $('.item input:checkbox').prop('checked', true);

    $('.form-opciones').each(function()
    {
        if ($(this).find('input:radio:checked').length == 0)
        {
            $(this).find('input:radio[value=all]').prop('checked', true);
        }
    });
}

function crearPlantillaFromFormulario()
{
    var plantilla = new Object();
    plantilla.id = $('#form-plantillas input:hidden[name=id-plantilla]').val();
    plantilla.nombre = $('#form-plantillas input:text[name=nombre-plantilla]').val();
    plantilla.idioma = $('#form-plantillas input:radio[name=radio-idiomas]:checked').val();

    if ($('#publicaciones-docentes').prop('checked'))
    {
        var publicacionesDocentes = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-docente-articulos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                publicacionesDocentes.maxAnyos = $('.form-opciones input:text[name=input-docente-articulos-years]').val().trim();
            }
            else
            {
                publicacionesDocentes.maxItems = $('.form-opciones input:text[name=input-docente-articulos-items]').val().trim();
            }

        }

        plantilla.publicacionesDocentes = publicacionesDocentes;
    }

    if ($('#congresos-docentes').prop('checked'))
    {
        var congresosDocentes = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-docente-congresos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                congresosDocentes.maxAnyos = $('.form-opciones input:text[name=input-docente-congresos-years]').val().trim();
            }
            else
            {
                congresosDocentes.maxItems = $('.form-opciones input:text[name=input-docente-congresos-items]').val().trim();
            }

        }

        plantilla.congresosDocentes = congresosDocentes;
    }

    if ($('#grupos').prop('checked'))
    {
        var gruposInvestigacion = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-grupos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                gruposInvestigacion.maxAnyos = $('.form-opciones input:text[name=input-grupos-years]').val().trim();
            }
            else
            {
                gruposInvestigacion.maxItems = $('.form-opciones input:text[name=input-grupos-items]').val().trim();
            }

        }

        plantilla.gruposInvestigacion = gruposInvestigacion;
    }

    if ($('#proyectos-cientificos').prop('checked'))
    {
        var proyectos = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-cientifico-proyectos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                proyectos.maxAnyos = $('.form-opciones input:text[name=input-cientifico-proyectos-years]').val().trim();
            }
            else
            {
                proyectos.maxItems = $('.form-opciones input:text[name=input-cientifico-proyectos-items]').val().trim();
            }

        }

        plantilla.proyectos = proyectos;
    }

    if ($('#contratos-cientificos').prop('checked'))
    {
        var contratos = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-cientifico-contratos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                contratos.maxAnyos = $('.form-opciones input:text[name=input-cientifico-contratos-years]').val().trim();
            }
            else
            {
                contratos.maxItems = $('.form-opciones input:text[name=input-cientifico-contratos-items]').val().trim();
            }

        }

        plantilla.contratos = contratos;
    }

    if ($('#publicaciones-cientificas').prop('checked'))
    {
        var publicaciones = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-cientifico-publicaciones][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                publicaciones.maxAnyos = $('.form-opciones input:text[name=input-cientifico-publicaciones-years]').val().trim();
            }
            else
            {
                publicaciones.maxItems = $('.form-opciones input:text[name=input-cientifico-publicaciones-items]').val().trim();
            }

        }

        plantilla.publicaciones = publicaciones;
    }

    if ($('#congresos-cientificos').prop('checked'))
    {
        var congresos = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-cientifico-congresos][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                congresos.maxAnyos = $('.form-opciones input:text[name=input-cientifico-congresos-years]').val().trim();
            }
            else
            {
                congresos.maxItems = $('.form-opciones input:text[name=input-cientifico-congresos-items]').val().trim();
            }

        }

        plantilla.congresos = congresos;
    }

    if ($('#tesis').prop('checked'))
    {
        var tesis = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-tesis][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                tesis.maxAnyos = $('.form-opciones input:text[name=input-tesis-years]').val().trim();
            }
            else
            {
                tesis.maxItems = $('.form-opciones input:text[name=input-tesis-items]').val().trim();
            }

        }

        plantilla.tesis = tesis;
    }

    if ($('#situacionProfesionalActiva').prop('checked'))
    {
        var situacionProfesionalActiva = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-situacionProfesionalActiva][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                situacionProfesionalActiva.maxAnyos = $('.form-opciones input:text[name=input-situacionProfesionalActiva-years]').val().trim();
            }
            else
            {
                situacionProfesionalActiva.maxItems = $('.form-opciones input:text[name=input-situacionProfesionalActiva-items]').val().trim();
            }

        }

        plantilla.situacionProfesionalActiva = situacionProfesionalActiva;
    }

    if ($('#situacionProfesionalAnterior').prop('checked'))
    {
        var situacionProfesionalAnterior = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-situacionProfesionalAnterior][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                situacionProfesionalAnterior.maxAnyos = $('.form-opciones input:text[name=input-situacionProfesionalAnterior-years]').val().trim();
            }
            else
            {
                situacionProfesionalAnterior.maxItems = $('.form-opciones input:text[name=input-situacionProfesionalAnterior-items]').val().trim();
            }

        }

        plantilla.situacionProfesionalAnterior = situacionProfesionalAnterior;
    }

    if ($('#doctorado').prop('checked'))
    {
        var doctorado = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-doctorado][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                doctorado.maxAnyos = $('.form-opciones input:text[name=input-doctorado-years]').val().trim();
            }
            else
            {
                doctorado.maxItems = $('.form-opciones input:text[name=input-doctorado-items]').val().trim();
            }

        }

        plantilla.doctorados = doctorado;
    }

    if ($('#docencia').prop('checked'))
    {
        var docencia = new Object();
        var opcion = $('.form-opciones input:radio[name=radio-docencia][value!=all]:checked');
        if ($(opcion).length != 0)
        {
            if ($(opcion).val() == 'years')
            {
                docencia.maxAnyos = $('.form-opciones input:text[name=input-docencia-years]').val().trim();
            }
            else
            {
                docencia.maxItems = $('.form-opciones input:text[name=input-docencia-items]').val().trim();
            }

        }

        plantilla.docencia = docencia;
    }




    return plantilla;
}

function rellenarFormularioPlantillas(plantilla)
{
    $('#form-plantillas input:hidden[name=id-plantilla]').val(plantilla.id);
    $('#form-plantillas input:text[name=nombre-plantilla]').val(plantilla.nombre);
    $('#form-plantillas input:radio[name=radio-idiomas][value=' + plantilla.idioma + ']').prop('checked', true);

    if (plantilla.publicacionesDocentes != null)
    {
        $('#publicaciones-docentes').prop('checked', true);

        if (plantilla.publicacionesDocentes.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-docente-articulos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-docente-articulos-years]').val(plantilla.publicacionesDocentes.maxAnyos);
        }
        else if (plantilla.publicacionesDocentes.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-docente-articulos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-docente-articulos-items]').val(plantilla.publicacionesDocentes.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-docente-articulos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.congresosDocentes != null)
    {
        $('#congresos-docentes').prop('checked', true);

        if (plantilla.congresosDocentes.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-docente-congresos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-docente-congresos-years]').val(plantilla.congresosDocentes.maxAnyos);
        }
        else if (plantilla.congresosDocentes.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-docente-congresos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-docente-congresos-items]').val(plantilla.congresosDocentes.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-docente-congresos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.gruposInvestigacion != null)
    {
        $('#grupos').prop('checked', true);

        if (plantilla.gruposInvestigacion.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-grupos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-grupos-years]').val(plantilla.gruposInvestigacion.maxAnyos);
        }
        else if (plantilla.gruposInvestigacion.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-grupos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-grupos-items]').val(plantilla.gruposInvestigacion.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-grupos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.proyectos != null)
    {
        $('#proyectos-cientificos').prop('checked', true);

        if (plantilla.proyectos.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-proyectos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-proyectos-years]').val(plantilla.proyectos.maxAnyos);
        }
        else if (plantilla.proyectos.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-proyectos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-proyectos-items]').val(plantilla.proyectos.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-cientifico-proyectos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.contratos != null)
    {
        $('#contratos-cientificos').prop('checked', true);

        if (plantilla.contratos.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-contratos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-contratos-years]').val(plantilla.contratos.maxAnyos);
        }
        else if (plantilla.contratos.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-contratos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-contratos-items]').val(plantilla.contratos.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-cientifico-contratos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.publicaciones != null)
    {
        $('#publicaciones-cientificas').prop('checked', true);

        if (plantilla.publicaciones.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-publicaciones][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-publicaciones-years]').val(plantilla.publicaciones.maxAnyos);
        }
        else if (plantilla.publicaciones.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-publicaciones][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-publicaciones-items]').val(plantilla.publicaciones.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-cientifico-publicaciones][value=all]').prop('checked', true);
        }
    }

    if (plantilla.congresos != null)
    {
        $('#congresos-cientificos').prop('checked', true);

        if (plantilla.congresos.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-congresos][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-congresos-years]').val(plantilla.congresos.maxAnyos);
        }
        else if (plantilla.congresos.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-cientifico-congresos][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-cientifico-congresos-items]').val(plantilla.congresos.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-cientifico-congresos][value=all]').prop('checked', true);
        }
    }

    if (plantilla.tesis != null)
    {
        $('#tesis').prop('checked', true);

        if (plantilla.tesis.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-tesis][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-tesis-years]').val(plantilla.tesis.maxAnyos);
        }
        else if (plantilla.tesis.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-tesis][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-tesis-items]').val(plantilla.tesis.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-tesis][value=all]').prop('checked', true);
        }
    }
    if (plantilla.situacionProfesionalActiva != null)
    {
        $('#situacionProfesionalActiva').prop('checked', true);

        if (plantilla.situacionProfesionalActiva.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalActiva][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-situacionProfesionalActiva-years]').val(plantilla.situacionProfesionalActiva.maxAnyos);
        }
        else if (plantilla.situacionProfesionalActiva.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalActiva][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-situacionProfesionalActiva-items]').val(plantilla.situacionProfesionalActiva.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalActiva][value=all]').prop('checked', true);
        }
    }

    if (plantilla.situacionProfesionalAnterior != null)
    {
        $('#situacionProfesionalAnterior').prop('checked', true);

        if (plantilla.situacionProfesionalAnterior.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalAnterior][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-situacionProfesionalAnterior-years]').val(plantilla.situacionProfesionalAnterior.maxAnyos);
        }
        else if (plantilla.situacionProfesionalActiva.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalAnterior][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-situacionProfesionalAnterior-items]').val(plantilla.situacionProfesionalAnterior.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-situacionProfesionalAnterior][value=all]').prop('checked', true);
        }
    }
    if (plantilla.doctorado != null)
    {
        $('#doctorado').prop('checked', true);

        if (plantilla.doctorado.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-doctorado][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-doctorado-years]').val(plantilla.doctorado.maxAnyos);
        }
        else if (plantilla.doctorado.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-doctorado][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-doctorado-items]').val(plantilla.doctorado.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-doctorado][value=all]').prop('checked', true);
        }
    }

    if (plantilla.docencia != null)
    {
        $('#docencia').prop('checked', true);

        if (plantilla.docencia.maxAnyos != 0)
        {
            $('.form-opciones input:radio[name=radio-docencia][value=years]').prop('checked', true);
            $('.form-opciones input:text[name=input-docencia-years]').val(plantilla.docencia.maxAnyos);
        }
        else if (plantilla.docencia.maxItems != 0)
        {
            $('.form-opciones input:radio[name=radio-docencia][value=items]').prop('checked', true);
            $('.form-opciones input:text[name=input-docencia-items]').val(plantilla.docencia.maxItems);
        }
        else
        {
            $('.form-opciones input:radio[name=radio-docencia][value=all]').prop('checked', true);
        }
    }
}