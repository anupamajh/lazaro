var pageNumber = 0;
var pageLimit = 10;
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
            document.location = "/lead-details/"+agentId + "/" + row.id;
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
            getPagedCountries(false);
        }
    });
}

function getPagedLeads(resetParams) {
    if (resetParams) {
        pageNumber = 0;
        totalRowsCount = 0;
    }
    $.ajax({
        url: '/get-leads-by-status',
        type: 'GET',
        data: {
            page: pageNumber
            ,status: status
            , pageLimit: pageLimit
            , sort: (sortField)
            , sortOrder: (sortOrder)
            , searchField: (searchField)
            , searchString: (searchString)
        },
        success: function (response) {
            if (response.success) {
                if (pageNumber === 0) {
                    //totalRowsCount = response.totalRecords;;
                   // updatePagination();
                }
                vieCountries.leads = [];
                vieCountries.leads = response.data;;
            } else {
                swal({
                    title: "OOPS!!!! Something went wrong!",
                    text: "Contact administrator",
                    type: "warning"
                });
            }
        },
        error: function (response) {
            swal({
                title: "OOPS!!!! Something went wrong!",
                text: "Contact Administrator",
                type: "warning"
            });
        }
    });
}