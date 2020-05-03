import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';

export interface Location {
  id:number;
  name: string;
  addressLine: string;
  city: string;
  state: string;
  zipcode:string;
  contactNumber:string;
  vehicleCapacity:number;

}
const ELEMENT_DATA: Location[] = [
  {id: 1, name:'Sunnyvale Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 2, name:'San Jose Suite',addressLine: '28 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 3, name:'San Antonio Suite',addressLine: '26 Vinney drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'1321312312',vehicleCapacity:10},
  {id: 4, name:'MountainView Suite',addressLine: '128 Washington drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 5, name:'Fremont Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 6, name:'Union city Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 7, name:'Menlo Park Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 8, name:'Gilbrae Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 9, name:'Milroy Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
  {id: 10, name:'Palo alto Suite',addressLine: '26 Showers drive', city:'Sunnyvale', state:'CA', zipcode:'94089', contactNumber:'23131313213',vehicleCapacity:10},
];
@Component({
  selector: 'app-locations-page',
  templateUrl: './locations-page.component.html',
  styleUrls: ['./locations-page.component.scss']
})
export class LocationsPageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'name', 'addressLine','city','state','zipcode', 'contactNumber','vehicleCapacity','star'];
  dataSource: MatTableDataSource<Location>;


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
