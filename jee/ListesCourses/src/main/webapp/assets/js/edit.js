$(function() {
	$liInput = $("#addItem")
	
	const generateArticle = (id, article) => {
		let $deleteBtn = $(`<button type="button" class="btn btn-danger btn-lg">×</button>`)
		let $li = $(`<li class="list-group-item d-flex align-items-center justify-content-between">
			<p class="mb-0">${article.nom} (x${article.qte})</p>
			<div></div>
		</li>`)
		
		$li.children("div").append($deleteBtn)
		
		$("main ul").append($li).append($liInput)
		
		let modal = new bootstrap.Modal($("#delete"))
		
		$deleteBtn.on("click", () => {
			$(".modal-body p").html(`Êtes-vous sûr de vouloir supprimer l'article <strong>${article.nom}</strong> ?`)
			$("#deleteBtn").on("click", () => {
				$.ajax({
					type: "delete",
					url: `./api/listesCourses/${id}/article`,
					data: `article=${article.nom}`,
					dataType: "json"
				}).done((ok) => {
					if(ok) {
						modal.hide()
						$li.remove()
					}
				})
			})
			
			modal.show()
		})
	}
	
	const updateDOM = ($form, nom) => {
		$form.children(".form-floating").remove()
		$form.prepend(`<h3 class="mb-0">${nom}</h3>`)
	}
	
	function formSubmit(event) {
		event.preventDefault()
		
		let url = $(this).attr("action")
		let id = $(this).attr("data-id")
		
		let $articleInput = $("input[name=article]")
		let $articleQty = $("input[name=qte]")
		
		$.ajax({
			type: "post",
			url: url,
			data: $(this).serialize(),
			dataType: "json"
		}).done(data => {
			if(id) {
				if(data) {
					generateArticle(id, data)
					$articleInput.val("")
					$articleQty.val(1)
				}
			} else {
				if(data && data.id && data.articles.length > 0) {
					location.replace(`./edit.html?id=${data.id}`)
				}
			}
		})
	}
	
	$("main form").on("submit", formSubmit)
	
	if($("form[data-id]").length) {
		let $form = $("form[data-id]")
		
		$.get($form.attr("action")).done(data => {
			if(data) {
				updateDOM($form, data.nom)
				
				for(let article of data.articles) {
					generateArticle(data.id, article)
				}
			} else {
				location.replace("./edit.html")
			}
		})
	}
})
