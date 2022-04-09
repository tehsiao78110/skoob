$(function () {

    $(".cancelbtn").click(function () {

        console.log($(this).val());
        let dataToServer = {
            orderid:$(this).val() ,
             orderaction :"cancel",
        };

        $.ajax({
            type: "post",
            dataType: "text",
            data: dataToServer,
            url:"/proj-skoob/pages/order.controllor",
            traditional: true,
          }).then(function (e) {
           setTimeout(() => { window.location.reload()}, 1600);
          });
    })
       $(".done").click(function () {

        console.log($(this).val());
        let dataToServer = {
            orderid:$(this).val() ,
             orderaction :"done",
        };
        

        $.ajax({
            type: "post",
            dataType: "text",
            data: dataToServer,
            url:"/proj-skoob/pages/order.controllor",
            traditional: true,
          }).then(function (e) {
           
            setTimeout(() => { window.location.reload()}, 1600);
           
          });
    })
    
    function alert2(){
        Swal.fire({
            icon: 'success',
            title: '謝謝惠顧!',
            showConfirmButton: false,
        })
    }
    
})