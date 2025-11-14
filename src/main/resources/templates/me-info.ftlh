<#import "common/template.ftlh" as t>
<@t.template user=user!>

<div class="cart-table-area section-padding-100">
    <div class="container-fluid">
        <div class="row">
            <div class="col-12 col-lg-8">
                    <div class="cart-title mt-50">
                        <h2>Товары пользователя ${user.name}</h2>
                    </div>

                <div class="cart-table clearfix">
                    <table class="table table-responsive">
                        <thead>
                        <tr>
                            <th>Фото</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Описание</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list products as product>

                        <tr>
                            <td class="cart_product_img">
                                <a href="/product/${product.id}">
                                    <img src="/images/${product.previewImageId}" alt="Товар"></a>
                            </td>
                            <td class="cart_product_desc">
                                <h5>
                                    <a class="p_title2" href="/product/${product.id}">
                                        ${product.title}
                                    </a>
                                </h5>
                            </td>
                            <td class="price">
                                <span>
                                     <a class="p_title2" href="/product/${product.id}">
                                        ${product.price} руб.
                                    </a>
                                </span>
                            </td>

                            <td class="cart_product_desc">
                               <div class="descr">
                                   <a class="p_title3" href="/product/${product.id}">
                                       ${product.description}
                                   </a>
                               </div>
                            </td>

                        </tr>
                        </#list>

                        </tbody>
                    </table>
                </div>
            </div>

            <div class="col-12 col-lg-4">
                <div class="cart-summary">
                <input type="image" src="" alt="">
                    <h5>Общая информация</h5>

                <div class="col-12 mb-3">
                    <ul class="summary-table">

<!--                        AVATAR-->
                        <form action="/me/avatar/change" method="post" enctype="multipart/form-data">
                            <label class="input-file">
                                <input type="hidden" name="_csrf" value="${_csrf.token}">
                                <input type="file" name="file1" id="f1" required>
                                <#if user.avatar??><img class="hover-img" style="border-radius:50%;width:200px; height:200px; cursor: pointer;" src="/images/${user.avatar.id}" alt="">
                                <#else><span class="input-file-btn">Выберите файл</span></#if>
                            <!--                        <span class="input-file-text">Изменить аватар</span>-->
                            </label> <script>
                            $('#f1').on('change', function() {
                            let file = this.files[0];
                $(this).closest('.input-file').find('.input-file-text').html(file.name);
                            });
                        </script>
                            <button class="btn amado-btn2" type="submit">Изменить</button>
                        </form >

<!--                        OTHER INFO-->

                        <li><span>Имя:</span> <span>${user.name}</span></li>
                        <li><span>Телефон:</span> <span>${user.phoneNumber}</span></li>
                        <li><span>Email:</span> <span>${user.email}</span></li>
                        <li><span>Date of created:</span> <span>${user.dateOfCreated}</span></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


</@t.template>