import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditVehicletypePageComponent } from './edit-vehicletype-page.component';

describe('EditVehicletypePageComponent', () => {
  let component: EditVehicletypePageComponent;
  let fixture: ComponentFixture<EditVehicletypePageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditVehicletypePageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditVehicletypePageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
