import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';

export interface Customer {
  id:number;
  name: string;
  addressLine: string;
  licenseInfo: string;
  startDate: string;
  endDate:string;
  verified:string;
  status:string;
  email :string;

}
const ELEMENT_DATA: Customer[] = [
  {id: 1, name:'Bob Maxwell',addressLine: '26 Showers drive', licenseInfo:'32424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 2, name:'Varun Krishna',addressLine: '26 Showers drive', licenseInfo:'92424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 3, name:'Damini Mukerji',addressLine: '26 Showers drive', licenseInfo:'1124324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 4, name:'Bob Maxwell',addressLine: '26 Showers drive', licenseInfo:'32424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 5, name:'John Maxwell',addressLine: '26 Showers drive', licenseInfo:'82424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 6, name:'Barbie Maxwell',addressLine: '26 Showers drive', licenseInfo:'72424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 7, name:'Mary Maxwell',addressLine: '26 Showers drive', licenseInfo:'32424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 8, name:'Bob Maxwell',addressLine: '26 Showers drive', licenseInfo:'22224324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 9, name:'Donald Maxwell',addressLine: '26 Showers drive', licenseInfo:'62424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
  {id: 10, name:'Bob Maxwell',addressLine: '26 Showers drive', licenseInfo:'52424324234', startDate:'04/01/2020', endDate:'01/10/2021', verified:'yes',status:'active', email:'abdcff@yahoo.com'},
];

@Component({
  selector: 'app-customers-page',
  templateUrl: './customers-page.component.html',
  styleUrls: ['./customers-page.component.scss']
})
export class CustomersPageComponent implements OnInit {

  displayedColumns: string[] = ['id', 'name', 'addressLine','licenseInfo','startDate','endDate', 'verified','status','email','star'];
  dataSource: MatTableDataSource<Customer>;


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  @ViewChild(MatSort, {static: true}) sort: MatSort;
  constructor() {
    this.dataSource = new MatTableDataSource(ELEMENT_DATA);

  }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

}
