<#import "common/template.ftlh" as t>
<@t.template user=user!>
<div class="cart-table-area section-padding-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-8">
                <div class="checkout_details_area mt-50 clearfix">
                    <div class="cart-title">
                        <h2>Регистрация пользователя</h2>
                    </div>

<form action="/registration" method="post">
    <div class="rov">
        <div class="col-12 mb-3">
            <input type="text" class="form-control" placeholder="Имя пользователя"
                   name="name" required>
        </div>
        <div class="col-12 mb-3">
            <input type="email" class="form-control" placeholder="Электронная почта"
                   name="email" required>
        </div>
        <div class="col-12 mb-3">
            <input type="text" class="form-control" placeholder="Номер телефона"
                   name="phoneNumber" required>
        </div>
        <div class="col-12 mb-3">
            <input type="password" class="form-control" placeholder="Пароль"
                   name="password" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="col-12 mb-3">
            <input type="submit" class="btn amado-btn2" value="Зарегистрироваться">
        </div>
    </div>
<!--    <p>Имя пользователя: <input type="text" name="name" required></p>-->
<!--    <p>Электронная почта: <input type="email" name="email" required></p>-->
<!--    <p>Номер телефона: <input type="text" name="phoneNumber" required></p>-->
<!--    <p>Пароль: <input type="password" name="password" required></p>-->
<!--    <input type="hidden" name="_csrf" value="${_csrf.token}">-->
<!--    <p><input type="submit" value="Зарегистрироваться"> </p>-->
</form>
<#if errorMessage??>
<p class="warn">${errorMessage} </p>
</#if>
            </div>
        </div>
    </div>
</div>
</div>
</@t.template>
