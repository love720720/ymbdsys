function detail(id) {
	if (id <= 0) {
		alertDanger();
		return;
	}
	location.href = "detailPrivilege.htm?id=" + id;
	return;
}