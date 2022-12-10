$(function(){
    /**
     * Вызывает функцию document_click каждый раз, когда пользователь щёлкает по документу
     */
    document.addEventListener('click',document_click);
    });
    
function document_click(e){
    if(e.target){
        let el=e.target;
        //При щелчке по полю alert закрывает его
        if(el.hasAttribute("role") && el.getAttribute("role")=="alert"){
            el.remove();
        }
    }
}