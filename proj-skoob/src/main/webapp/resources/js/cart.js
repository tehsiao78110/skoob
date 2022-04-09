$(function () {

  // 定義 ajax 函數
  function ajaxPost(dataToServer) {
    $.ajax({
      type: "post",
      dataType: "text",
      data: dataToServer,
      url: "/proj-skoob/pages/cart.controller",
      traditional: true,
    }).then(function (e) {
      window.location.reload();
      // history.go(0);
    });
  }

  function ajaxPut(dataToServer) { 
    $.ajax({
      type: "put",
      dataType: "text",
      data: JSON.stringify(dataToServer),
      url: "/proj-skoob/pages/cart.controller",
      traditional: true,
    }).then(function (e) {
      window.location.reload();
    });
  }

  function ajaxDelete(dataToServer) {
    $.ajax({
      type: "delete",
      dataType: "text",
      data: JSON.stringify(dataToServer),
      url: "/proj-skoob/pages/cart.controller",
      traditional: true,
    }).then(function (e) {
      window.location.reload();
    });
  }

  // 刪除「多筆」商品
  $("#btnDeleteAll").click(function () {
    let checkid = [];
    $("input[name='checkbox']:checked").each(function (i) {
      checkid[i] = $(this).val();
    });

    let dataToServer = {
      checkid: checkid,
      cartAction: "deleteMulti",
    };

    ajaxDelete(dataToServer);

  });

  // 刪除「單筆」商品
  $(".delete").click(function () {
    checkid = $(this).val();

    let dataToServer = {
      checkid: checkid,
      cartAction: "delete",
    };

    ajaxDelete(dataToServer);
    
  });

  // 數字框 - 輸入商品數量
  $(".btnNum").focusout(function () {
    let number = parseInt($(this).val());
    let price = parseInt($(this).closest("li").prev().html());
    let subtotal = number * price;
    let productid = $(this).prev().val();
    let Inventory = parseInt($(this).next().next().val());

    // 處理「不合法」的商品數
    if (number <= 0 || parseInt(number) > parseInt(Inventory)) {
      let title = $(this).closest("li").prev().prev().find(".cart_product_title").html();
      let setNum = (number <= 0) ? 1 : Inventory;
      number = setNum;
      subtotal = number * price;
      $(this).val(setNum);
      alert("下列你所購買的商品已無法購買或是無足夠庫存\n" + title);
    }
    
    let dataToServer = {
      cartAction: "put",
      productid: productid,
      number: number,
      subtotal: subtotal,
    };
    ajaxPut(dataToServer);

  });

  // 按鈕 - 商品數量「加一」
  $(".btnNumAdd").click(function () {
    // 從網頁上取得資料
    let number = parseInt($(this).prev().val()) + 1;
    let price = parseInt($(this).closest("li").prev().html());
    let subtotal = number * price;
    let productid = $(this).val();
    let Inventory = parseInt($(this).next().val());

    if (parseInt(number) <= parseInt(Inventory)) {
      let dataToServer = {
        cartAction: "put",
        productid: productid,
        number: number,
        subtotal: subtotal,
      };

      ajaxPut(dataToServer);

    } else {
      let title = $(this).closest("li").prev().prev().find(".cart_product_title").html();
      $(this).prev().val(Inventory);
      alert("下列你所購買的商品已無法購買或是無足夠庫存\n" + title);
    }

  });

  // 按鈕 - 商品數量「減一」
  $(".btnNumDec").click(function () {
    let number = parseInt($(this).next().val()) - 1;
    console.log("number = " + number);
    let price = parseInt($(this).closest("li").prev().html());
    console.log("price = " + price);
    let subtotal = number * price;
    console.log("subtotal = " + subtotal);
    let productid = $(this).val();
    console.log("productid = " + productid);

    if (parseInt(number + 1) > parseInt(1)) {
      let dataToServer = {
        cartAction: "put",
        productid: productid,
        number: number,
        subtotal: subtotal,
      };
      ajaxPut(dataToServer);
    } else {
      let dataToServer = {
        checkid: productid,
        cartAction: "delete",
      };
      ajaxDelete(dataToServer);
    }
  });

  // 複選框的『前端設定』
  $("#checkboxAll").click(function () {
    let checkedValue = $(this).prop("checked");
    $(".form-isCheck").prop("checked", checkedValue);
  });

  $(".form-isCheck").click(function () {
    // checkbox 的總個數
    var numOfAll = $(".form-isCheck").length;
    // checkbox 的勾選個個數
    var numOfSelect = $(".form-isCheck:checked").length;
    // 直接將『個數判斷』的結果，當成 checked 屬性值
    $('#checkboxAll').prop("checked", numOfAll == numOfSelect) 
  });
});
