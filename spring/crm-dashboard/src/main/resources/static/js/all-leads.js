var pageNumber = 1;
var pageLimit = 20;
var searchField = null;
var sortField = 'creationTime';
var sortOrder = 'DESC';
var searchString = null;
var totalRowsCount = 0;

var vieCountries = new Vue({
    el: "#records-list",
    data: {
        leads : []
    },
    methods: {
        navigateToEditORView: function (row) {
            //document.location = "/masters/country/form/" + row.id;
        }
    }
});

$(document).ready(function () {
    initEvents();
    getPagedLeads(true);
});

function initEvents() {

}

function updatePagination() {
    $('#table-pagination').pagination({
        edges: 1
        , items: totalRowsCount
        , itemsOnPage: pageLimit
        , cssStyle: 'dark-theme'
        , onInit: function () {

        }
        , onPageClick: function (_pageNumber, _event) {
            _event.preventDefault();
            pageNumber = _pageNumber;
            getPagedLeads(false);
        }
    });
}

function getPagedLeads(resetParams) {
    if (resetParams) {
        pageNumber = 1;
        totalRowsCount = 0;
    }
    $.ajax({
        url: '/get-paged-leads',
        type: 'GET',
        data: {
            page: pageNumber
            , pageLimit: pageLimit
            , sort: (sortField)
            , sortOrder: (sortOrder)
            , searchField: (searchField)
            , searchString: (searchString)
        },
        success: function (response) {
            if (response.success) {
                if (pageNumber === 1) {
                    totalRowsCount = response.totalRecords;;
                    updatePagination();
                }
                vieCountries.leads = [];
                vieCountries.leads = response.data;;
            } else {
                swal({
                    title: "OOPS!!!! Something went wrong!",
                    text: response.errorMsg,
                    type: "warning"
                });
            }
        },
        error: function (response) {
            swal({
                title: "OOPS!!!! Something went wrong!",
                text: response,
                type: "warning"
            });
        }
    });
}