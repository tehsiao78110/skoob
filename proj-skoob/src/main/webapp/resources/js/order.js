$(function () {

    $(".cancelbtn").click(function () {
        console.log($(this).val());
        let dataToServer = {
            orderid: $(this).val(),
            orderAction: "cancel",
        };

        $.ajax({
            type: "put",
            dataType: "text",
            data: JSON.stringify(dataToServer),
            url: "/proj-skoob/pages/order.controllor",
            traditional: true,
        }).then(function (e) {
        	Swal.fire({
			  icon: 'error',
			  title: '已取消訂單',			  
			  timer: 1500
			})
			
            setTimeout(() => {
                window.location.reload();
            }, 1600);
            
        });
    });

    $(".done").click(function () {
        console.log($(this).val());
        let dataToServer = {
            orderid: $(this).val(),
            orderAction: "done",
        };

        $.ajax({
            type: "put",
            dataType: "text",
            data: JSON.stringify(dataToServer),
            url: "/proj-skoob/pages/order.controllor",
            traditional: true,
        }).then(function (e) {
        	Swal.fire({
			  icon: 'success',
			  title: '已完成訂單',			  
			  timer: 1500
			})
			
            setTimeout(() => {
                window.location.reload();
           }, 1600);
           
        });
    });

//    function alert2() {
//        Swal.fire({
//            icon: "success",
 //           title: "謝謝惠顧!",
 //           showConfirmButton: false,
  //      });
 //   }
    
    
});
