import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVehicletypePageComponent } from './add-vehicletype-page.component';

describe('AddVehicletypePageComponent', () => {
  let component: AddVehicletypePageComponent;
  let fixture: ComponentFixture<AddVehicletypePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddVehicletypePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddVehicletypePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
