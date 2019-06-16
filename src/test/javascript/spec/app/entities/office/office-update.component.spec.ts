/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { D2GcrmTestModule } from '../../../test.module';
import { OfficeUpdateComponent } from 'app/entities/office/office-update.component';
import { OfficeService } from 'app/entities/office/office.service';
import { Office } from 'app/shared/model/office.model';

describe('Component Tests', () => {
  describe('Office Management Update Component', () => {
    let comp: OfficeUpdateComponent;
    let fixture: ComponentFixture<OfficeUpdateComponent>;
    let service: OfficeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [D2GcrmTestModule],
        declarations: [OfficeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(OfficeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(OfficeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(OfficeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Office('123');
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Office();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
