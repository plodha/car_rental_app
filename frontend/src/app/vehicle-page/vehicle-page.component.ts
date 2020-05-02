import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort} from '@angular/material/sort';
import {MatTableDataSource} from '@angular/material/table';
import {SelectionModel} from '@angular/cdk/collections';

export interface VehicleElement {
  id:number;
  VehicleType: string;
  model: string;
  make: string;
  year: string;
  vin:string;
  licensePlate:string;
  location:string;
  status:string;
}

const ELEMENT_DATA: VehicleElement[] = [
  {id: 1, VehicleType:'Compact',model: 'Hydrogen', make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 2,VehicleType:'Compact', model: 'Helium', make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 3,VehicleType:'Compact', model: 'Lithium', make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 4,VehicleType:'Compact', model: 'Beryllium',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 5,VehicleType:'Compact', model: 'Boron',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 6,VehicleType:'Compact', model: 'Carbon',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 7, VehicleType:'Compact',model: 'Nitrogen',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 8,VehicleType:'Compact', model: 'Oxygen',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 9,VehicleType:'Compact', model: 'Fluorine',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'},
  {id: 10,VehicleType:'Compact', model: 'Neon',make:'Honda', year:'2019', vin:'gshfjshjhjsh8383748234', licensePlate:'HGTY567',location:'Sunnyvale',status:'Available'}
];
@Component({
  selector: 'app-vehicle-page',
  templateUrl: './vehicle-page.component.html',
  styleUrls: ['./vehicle-page.component.scss']
})
export class VehiclePageComponent implements OnInit {
  displayedColumns: string[] = ['id', 'VehicleType', 'model','make','year','licensePlate', 'vin','location','status','star'];
 dataSource: MatTableDataSource<VehicleElement>;

 @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
 @ViewChild(MatSort, {static: true}) sort: MatSort;


 constructor() {
   // Create 100 users
   // Assign the data to the data source for the table to render
   this.dataSource = new MatTableDataSource(ELEMENT_DATA);

 }

 ngOnInit() {
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
