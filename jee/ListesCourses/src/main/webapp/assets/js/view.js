$(function() {
	const generateArticle = (id, article) => {
		let $li = $(`<li class="list-group-item">
			<div class="form-check">
				<input class="form-check-input" type="checkbox" value="" id="${id}">
				<label class="form-check-label h4" for="${id}">
					${article.nom} (x${article.qte})
				</label>
			</div>
		</li>`)
		
		let $input = $li.find("input")
		let $label = $li.find("label")
		
		$input.on("change", () => {
			$label.toggleClass("text-decoration-line-through")
			
			if($label.hasClass("text-decoration-line-through")) {
				$li.appendTo("main ul")
			} else {
				$li.prependTo("main ul")
			}
		})
		
		$li.appendTo("main ul")
	}
	
	$("#uncheck").on("click", () => {
		$(":checked").prop("checked", false).trigger("change")
		
	})
	
	if($("ul[data-id]").length) {
		let $ul = $("ul[data-id]")
		let id = 0;
		
		$.get(`./api/listesCourses/${$ul.attr("data-id")}`).done(data => {
			if(data) {
				$("h3").text(data.nom)
				
				for(let article of data.articles) {
					generateArticle(id, article)
					id++;
				}
			} else {
				location.replace("./")
			}
		})
	}
})