<#import "common/template.ftlh" as t>
<@t.template user=user!>
<div class="cart-table-area section-padding-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-8">
                <div class="checkout_details_area mt-50 clearfix">
                    <div class="cart-title">
                    <h2>Авторизация пользователя</h2>
                </div>
<form action="/login" method="post">

    <div class="rov">
        <div class="col-12 mb-3">
            <input type="email" class="form-control" placeholder="Электронная почта"
                   name="username" required>
        </div>
        <div class="col-12 mb-3">
            <input type="password" class="form-control" placeholder="Пароль"
                   name="password" required>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <div class="col-12 mb-3">
            <input type="submit" class="btn amado-btn2" value="Войти">
        </div>
    </div>



</form>
<p><a href="/registration">Регистрация нового пользователя</a></p>

                </div>
            </div>
        </div>
    </div>
</div>
</@t.template>

