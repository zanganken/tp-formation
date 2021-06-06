$(function() {
	const $loading = $(`<li class="list-group-item d-flex align-items-center">
		<div class="spinner-border" role="status" aria-hidden="true"></div>
		<div class="ms-3">Chargement...</div>
	</li>`)
	
	const $noItems = $(`<li class="list-group-item">
		Aucuns enregistrements trouvés. Créez votre première liste en cliquant <a href="./edit.html">ici</a>.
	</li>`)
	
	const generateLC = lc => {
		let id = lc.id
		let nom = lc.nom
		
		let $deleteBtn = $(`<button type="button" class="btn btn-danger btn-lg">×</button>`)
		let $li = $(`<li class="list-group-item d-flex align-items-center justify-content-between">
			<h3>${nom}</h3>
			<div class="btn-group" role="group">
				<a class="btn btn-outline-dark btn-lg" href="./view.html?id=${id}">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart-fill" viewBox="0 0 16 16">
						<path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
					</svg>
				</a>
				<a class="btn btn-outline-primary btn-lg" href="./edit.html?id=${id}">
					<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-pencil-fill" viewBox="0 0 16 16">
						<path d="M12.854.146a.5.5 0 0 0-.707 0L10.5 1.793 14.207 5.5l1.647-1.646a.5.5 0 0 0 0-.708l-3-3zm.646 6.061L9.793 2.5 3.293 9H3.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.207l6.5-6.5zm-7.468 7.468A.5.5 0 0 1 6 13.5V13h-.5a.5.5 0 0 1-.5-.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.5-.5V10h-.5a.499.499 0 0 1-.175-.032l-.179.178a.5.5 0 0 0-.11.168l-2 5a.5.5 0 0 0 .65.65l5-2a.5.5 0 0 0 .168-.11l.178-.178z"/>
					</svg>
				</a>
			</div>
		</li>`)
		
		$li.children(".btn-group").append($deleteBtn)
		$li.appendTo("main ul")
		
		let modal = new bootstrap.Modal($("#delete"))
		
		$deleteBtn.on("click", () => {
			$(".modal-body p").html(`Êtes-vous sûr de vouloir supprimer la liste <strong>${nom}</strong> ?`)
			$("#deleteBtn").on("click", () => {
				$.ajax({
					type: "delete",
					url: `./api/listesCourses/${id}`,
					dataType: "json"
				}).done((ok) => {
					if(ok) {
						modal.hide()
						$li.remove()
						
						if($("main ul li").length == 0) {
							$("main ul").append($noItems)
						}
					}
				})
			})
			
			modal.show()
		})
	}
	
	const getAll = () => {
		$("main ul").html($loading)
		
		$.get("./api/listesCourses").done((data) => {
			if(data && data.length > 0) {
				$loading.remove()
				
				for(lc of data) {
					generateLC(lc)
				}
			} else {
				$("main ul").html($noItems)
			}
		})
	}
	
	getAll()
})
